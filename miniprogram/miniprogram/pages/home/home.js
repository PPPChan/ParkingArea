// miniprogram/pages/home.js
import regeneratorRuntime from '../../utils/runtime'

var QQMapWX = require('../../qqmap-wx-jssdk');
var qqmapsdk;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    swiperImgUrls: [
      {
        url: 'https://636c-cloud1-7g0nn22565c366ae-1305939509.tcb.qcloud.la/%E8%BD%AE%E6%92%AD.PNG?sign=58e24c19be7bcb777708692d41eae931&t=1623581745',
      },
    ],
    modalShow: false,
    nickName: "1",
    parkingList: Array,
    test: 1,
  },

  //距离排序
  distSort(){
    this.data.parkingList.sort(
      function compare(x,y) {
        console.log("x:")
        console.log(x.dist)
        if(x.dist != undefined && y.dist != undefined){
          return x.dist[0]-y.dist[0]
        }else{
          return 9999
        }
      }
    )
    console.log("sort")
    console.log(this.data.parkingList)
    this.setData({
      parkingList: this.data.parkingList
  })
  },


  //停车功能
  onPark() {
    if (wx.getStorageSync('nickName')) {
      //已获取授权
      wx.navigateTo({
        url: '../park/park',
      })
    } else {
      //未授权

      this.setData({
        modalShow: true,
      })
    }
  },
  bind() {
    wx.navigateTo({
      url: '../bindNumber/bindNumber',
    })
  },

  onLoginSuccess(event) {
    console.log(event)
  },
  onLoginFail() {
    wx.showModal({
      title: '授权用户才能停车'
    })
  },
  

  refreshParking() {
    const _self = this
    wx.cloud.callFunction({
      name: 'parking',
      data: {
        $url: "list"
      }
    }).then(async (res) => {
      const result = res.result
      console.log(result.data)
      this.setData({
        parkingList: result.data
      })
      // 实例化API核心类
      qqmapsdk = new QQMapWX({
        key: 'Q23BZ-VU7EQ-R2V5L-GDB3Q-TB2VV-FQFO3'
      });
      wx.getLocation({
        type: 'wgs84',
        success(res) {
          console.log(res)
          const latitude = res.latitude
          const longitude = res.longitude
          const speed = res.speed
          const accuracy = res.accuracy
          console.log(accuracy)
          // console.log("pp" + _self.data.parkingList)
          for (var i = 0; i < _self.data.parkingList.length; i++) {
            console.log(_self.data.parkingList[i])
            _self.getDist(_self.data.parkingList[i].parkingAddress,i)
            
          }
          console.log(_self.data.parkingList)
          
        },
        fail() {
          console.log("111")
        }
      })





    })

  },


  getDist(e,i) {
    var _this = this;
    //调用地址解析接口
    qqmapsdk.geocoder({
      //获取表单传入地址
      // address: e.detail.value.geocoder, //地址参数，例：固定地址，address: '北京市海淀区彩和坊路海淀西大街74号'
      address: e,
      success: function (res) {//成功后的回调
        console.log(res);
        var res = res.result;
        var latitude = res.location.lat;
        var longitude = res.location.lng;
        //根据地址解析在地图上标记解析地址位置
        _this.setData({ // 获取返回结果，放到markers及poi中，并在地图展示
          markers: [{
            id: 0,
            title: res.title,
            latitude: latitude,
            longitude: longitude,
            iconPath: './resources/placeholder.png',//图标路径
            width: 20,
            height: 20,
            callout: { //可根据需求是否展示经纬度
              content: latitude + ',' + longitude,
              color: '#000',
              display: 'ALWAYS'
            }
          }],
          poi: { //根据自己data数据设置相应的地图中心坐标变量名称
            latitude: latitude,
            longitude: longitude
          }
        });
      },
      fail: function (error) {
        console.error(error);

        //获取不到设置为9999999
        var set = "parkingList["+i+"].dist"
        _this.setData({
          set: 99999999
        })
      },
      complete: function (res) {
        console.log(res.result.location.lng + "," + res.result.location.lat);
        const to = res.result.location.lat + "," + res.result.location.lng
        console.log(to)
        var set = "parkingList["+i+"].dist"
        //调用距离计算接口
        qqmapsdk.calculateDistance({
          //mode: 'driving',//可选值：'driving'（驾车）、'walking'（步行），不填默认：'walking',可不填
          //from参数不填默认当前地址
          //获取表单提交的经纬度并设置from和to参数（示例为string格式）
          from: '', //若起点有数据则采用起点坐标，若为空默认当前地址
          to: to, //终点坐标
          success: function (res) {//成功后的回调
            console.log(res);
            var res = res.result;
            var dis = [];
            for (var i = 0; i < res.elements.length; i++) {
              dis.push(res.elements[i].distance); //将返回数据存入dis数组，
            }
            // _this.setData({ //设置并更新distance数据
            //   [set]: dis,
            //   test: dis
            // });


            setTimeout(
              _this.setData({ //设置并更新distance数据
                [set]: dis,
                test: dis
              })
            , 500);
            console.log("test"+_this.data.test)
            console.log("pl"+_this.data.parkingList[i].dist)
            
          },
          fail: function (error) {
            //获取不到设置为9999999
            var set = "parkingList["+i+"].dist"
            _this.setData({
              set: 99999999
            })
            console.error(error);
          },
          complete: function (res) {
            console.log(res.result.elements[0].distance);
            // return res.result.elements[0].distance;
            _this.distSort()
          }
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    if (wx.getStorageSync('nickName')) {
      console.log("11")
      this.setData({
        modalShow: false
      })
    } else {
      console.log("2")
      this.setData({
        modalShow: true
      })
    }

    this.refreshParking()

    

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    // this.getUserInfo()
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})