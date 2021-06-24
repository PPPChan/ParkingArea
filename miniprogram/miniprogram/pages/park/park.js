// miniprogram/pages/order/order.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    parkingList: Array,
    parkingIsSelect: false,
    parkingSelect: -1,
    parking:Array,
    numberList:Array,
    numberSelect: "",
  },

  selectParking: function(event){
    const ds = event.currentTarget.dataset
    console.log(ds)
    this.setData({
      parkingIsSelect:true,
      parkingSelect:ds.parkingid
    })
    wx.setNavigationBarTitle({
      title: '请选择车牌'
    })
    
    wx.showLoading({
      title: '加载中',
    })
    wx.cloud.callFunction({
      name: 'licenseplate',
      data: {
        $url: "personal"
      }
    }).then(async(res)=>{
      const result = res.result
      console.log(result.data)
      this.setData({
        numberList: result.data
      })
      
      wx.hideLoading()
    })
    
  },


  bindPickerChange: function(e) {
    console.log(e)
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value,
      numberSelect: this.data.numberList[e.detail.value].licensePlateNumber,
    })
    console.log(this.data.index)
    console.log(this.data.numberSelect)
  },

  onPark: function(){
    wx.showLoading({
      title: '创建订单中',
    })
    wx.cloud.callFunction({
      name: 'getOrderList',
      data: {
        $url: "create",
        licensePlateNumber: this.data.numberSelect,
        parkingId: this.data.parkingSelect
      }
    }).then(async(res)=>{
      
      console.log(res)
      if(res.result.code == 0){
        console.log(res)
        wx.hideLoading()
        wx.showToast({
          title: '创建订单成功',
        })
        setTimeout(()=>{
          wx.navigateBack({
          })
        },1500)
      }else{
        wx.hideLoading()
        wx.showToast({
          title: res.result.msg,
          icon: 'error'
        })
      }
      
    setTimeout(()=>{
      wx.navigateBack({
      })
    },1500)
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
      name: 'parking',
      data: {
        $url: "list"
      }
    }).then(async(res)=>{
      const result = res.result
      console.log(result.data)
      this.setData({
        parkingList: result.data
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