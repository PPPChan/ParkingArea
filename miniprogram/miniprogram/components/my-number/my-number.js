// components/my-number/my-number.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    numberList: Array
  },

  /**
   * 组件的初始数据
   */
  data: {
  },
  /**
   * 组件的方法列表
   */
  methods: {
    reload(){
      this.triggerEvent("reload")
    },
    bind(){
      wx.navigateTo({
        url: '../bindNumber/bindNumber',
      })
    },
    delete (event){
      var _self = this
      wx.showModal({
        title: '提示',
        content: `确定删除${event.currentTarget.dataset.number}车牌吗？`,
        success (res) {
          if (res.confirm) {
            console.log('用户点击确定')
            wx.cloud.callFunction({
              name: 'licenseplate',
              data: {
                $url: "unbind",
                licensePlateNumber: event.currentTarget.dataset.number,
              }
            }).then(async(res)=>{
              const result = res.result
              console.log(result)
              wx.hideLoading()
              if(result.code == "0"){
                wx.showToast({
                  title: `解绑车牌号成功,车牌号：${result.data}`,
                  icon: 'none',
                });
                _self.reload()
              }else{
                wx.showToast({
                  title: result.msg,
                  icon: 'none',
                });
              }
            })
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
      })
    }
  }
})
