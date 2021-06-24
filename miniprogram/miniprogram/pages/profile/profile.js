// miniprogram/pages/profile/profile.js
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    
    avatarUrl: "",
    nickName: "请先授权登录，再进行操作"
  },

  mynumber: function(){
    wx.navigateTo({
      url: '../my-number/my-number',
    })
  },
  mycard:function(){
    wx.navigateTo({
      url: '../my-card/my-card',
    })
  },
  tomybill:function(){
    wx.navigateTo({
      url: '../my-bill/my-bill',
    })
  },
  logout: function(){
    wx.showModal({
      title: '智能泊车提醒',
      content: '确认要退出当前账户吗？',
      success (res) {
        if (res.confirm) {
          wx.clearStorage()
        } else if (res.cancel) {
          console.log('用户点击取消退出')
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      avatarUrl: wx.getStorageSync('avatarUrl'),
      nickName: wx.getStorageSync('nickName'),
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
    this.setData({
      avatarUrl: wx.getStorageSync('avatarUrl'),
      nickName: wx.getStorageSync('nickName')
    })
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