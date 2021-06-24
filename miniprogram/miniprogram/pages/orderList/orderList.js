// miniprogram/pages/order/order.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderList: [],
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.showLoading({
      title: '加载中',
    })
    console.log(wx.getStorageSync('openid'))
    wx.cloud.callFunction({
      name: 'getOrderList',
      data: {
        $url: "personal"
      }
    }).then(async(res)=>{
      const result = res.result
      console.log(result)
      this.setData({
        orderList: result.data
      })
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
    wx.showLoading({
      title: '加载中',
    })
    console.log(wx.getStorageSync('openid'))
    wx.cloud.callFunction({
      name: 'getOrderList',
      data: {
        $url: "personal"
      }
    }).then(async(res)=>{
      const result = res.result
      console.log(result)
      this.setData({
        orderList: result.data
      })
    })
    wx.stopPullDownRefresh()
    wx.hideLoading()
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