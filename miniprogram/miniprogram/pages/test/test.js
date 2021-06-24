// miniprogram/pages/test/test.js
var QQMapWX = require('../../qqmap-wx-jssdk');
var qqmapsdk;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    address: ['深圳市南山区西丽街道', '广东省汕尾市海丰县二环南路', '珠海市金湾区吉林大学珠海学院', '汕尾市海丰县人民西路'],
    desc: ""
  },
  getAddress(e) {
    var _this = this;
    //调用地址解析接口
    qqmapsdk.geocoder({
      //获取表单传入地址
      // address: e.detail.value.geocoder, //地址参数，例：固定地址，address: '北京市海淀区彩和坊路海淀西大街74号'
      address:e,
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
      },
      complete: function (res) {
        console.log(res.result.location.lng+","+res.result.location.lat);
        const to = res.result.location.lat+","+res.result.location.lng
        console.log(to)
        //调用距离计算接口
        qqmapsdk.calculateDistance({
          //mode: 'driving',//可选值：'driving'（驾车）、'walking'（步行），不填默认：'walking',可不填
          //from参数不填默认当前地址
          //获取表单提交的经纬度并设置from和to参数（示例为string格式）
          from: '', //若起点有数据则采用起点坐标，若为空默认当前地址
          to: to, //终点坐标
          success: function(res) {//成功后的回调
            console.log(res);
            var res = res.result;
            var dis = [];
            for (var i = 0; i < res.elements.length; i++) {
              dis.push(res.elements[i].distance); //将返回数据存入dis数组，
            }
            _this.setData({ //设置并更新distance数据
              distance: dis
            });
          },
          fail: function(error) {
            console.error(error);
          },
          complete: function(res) {
            console.log(res);
          }
      });
      }
    })
  },


  getDist(e){
    var _this = this;
    //调用距离计算接口
    qqmapsdk.calculateDistance({
        //mode: 'driving',//可选值：'driving'（驾车）、'walking'（步行），不填默认：'walking',可不填
        //from参数不填默认当前地址
        //获取表单提交的经纬度并设置from和to参数（示例为string格式）
        from: '', //若起点有数据则采用起点坐标，若为空默认当前地址
        to: e, //终点坐标
        success: function(res) {//成功后的回调
          console.log(res);
          var res = res.result;
          var dis = [];
          for (var i = 0; i < res.elements.length; i++) {
            dis.push(res.elements[i].distance); //将返回数据存入dis数组，
          }
          _this.setData({ //设置并更新distance数据
            distance: dis
          });
        },
        fail: function(error) {
          console.error(error);
        },
        complete: function(res) {
          console.log(res);
        }
    });
},
//在Page({})中使用下列代码
//事件触发，调用接口


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const _self = this
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
        
        for(var i=0;i<_self.data.address.length;i++){
          console.log(_self.data.address[i])
          _self.getAddress(_self.data.address[i])
        }

      },
      fail() {
        console.log("111")
      }
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

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