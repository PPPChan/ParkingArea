// miniprogram/pages/orderDetail/orderDetail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderInfo: Array
  },
  

  goToPay: function(event){
    const ds = event.currentTarget.dataset
    wx.navigateTo({
      url: `../pay/pay?orderId=${ds.orderid}`,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: '加载中',
    })
    console.log(options.orderId)
    wx.cloud.callFunction({
      name: 'getOrderList',
      data: {
        $url: "detail",
        orderId: options.orderId
      }
    }).then(async(res)=>{
      const result = res.result
      console.log(result)
      this.setData({
        orderInfo: result.data
      })
      
      wx.hideLoading()
    })
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