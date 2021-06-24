// miniprogram/pages/pay/pay.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderInfo: Object,
    min: 4,
    second: 59,
    a:null,
  },
  preview: function () {
    // wx.previewImage({
    //   urls: ['https://636c-cloud1-7g0nn22565c366ae-1305939509.tcb.qcloud.la/enQrcode.png?sign=1ed296f4ec3568bbeada2799cc3f3fe3&t=1621755069']
    // })
    wx.navigateTo({
      url: 'https://636c-cloud1-7g0nn22565c366ae-1305939509.tcb.qcloud.la/enQrcode.png?sign=63723158f20e1b49dea6f2ef16525177&t=1621761375',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: '加载中',
    })
    wx.cloud.callFunction({
      name: 'getOrderList',
      data: {
        $url: "pay",
        orderId: options.orderId
      }
    }).then(async (res) => {
      const result = res.result
      console.log(result)
      this.setData({
        orderInfo: result.data
      })
      console.log(this.data.orderInfo)
    })
    wx.hideLoading()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var _self = this
    _self.data.a = setInterval(function async() {
      //循环执行代码 
      if (!(_self.data.min==0 && _self.data.second==0)) {
        if(_self.data.second ==0 ){
          _self.setData({
            second: 59,
            min: _self.data.min-1,
          })
        }else{
          _self.setData({
            second: _self.data.second-1
          })
        }

        //每隔10秒验证是否已支付
        if(_self.data.second%10==0){
          wx.cloud.callFunction({
            name: 'getOrderList',
            data: {
              $url: "detail",
              orderId: _self.data.orderInfo.orderId
            }
          }).then((res) => {
            const result = res.result
            console.log(result)
            if(result.data.payStatus == 1){
              wx.showToast({
                title: '订单支付成功',
                icon: 'success'
              })
              setTimeout(()=>{
                wx.navigateBack({
                })
              },1500)
              clearInterval(a)
            }
          })
        }
      }else{
        console.log("听得到我讲话吗")
        wx.showToast({
          title: '订单支付已过期！请返回后重新支付！',
          icon: 'error'
        })
        setTimeout(()=>{
          wx.navigateBack({
          })
        },1500)
      }
      console.log("循环进行中")
    }, 1000) //循环时间 这里是1秒

    console.log("循环结束")
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
    clearInterval(this.data.a)
    console.log("卸载")
    
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