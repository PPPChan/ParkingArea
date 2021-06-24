var app = getApp();
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    modalShow: Boolean,

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
    getPhoneNumber(e) {
      var that = this;
      wx.cloud.callFunction({
        name: 'getMobile',
        data: {
          weRunData: wx.cloud.CloudID(e.detail.cloudID),
        }
      }).then(res => {
        that.setData({
          mobile: res.result,
        })

      }).catch(err => {
        console.error(err);
      });
    },
    onGetUserInfo(event) { // 获取用户信息
      const userInfo = event.detail.userInfo
      if (userInfo) { // 用户允许授权
        this.setData({
          modalShow: false
        })
        this.triggerEvent('loginSuccess', userInfo) // 给父组件传用户数据
      } else { // 用户拒绝授权
        this.triggerEvent('loginFail')
      }
    },
    getUserInfo() {
      wx.getUserProfile({
        desc: '用于确认用户信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
        success: (res) => {
          console.log(res)
          wx.setStorageSync('nickName', res.userInfo.nickName)
          wx.setStorageSync('avatarUrl', res.userInfo.avatarUrl)
          this.setData({
            modalShow: false
          })
          console.log(this.showModal)

          console.log("调用云函数")
          //获取openid
          wx.cloud.callFunction({
            name: 'getopenid',
            data: {
              nickName: wx.getStorageSync('nickName')
            }
          }).then(async (res) => {
            wx.setStorageSync('openid', res.result.openid)
            console.log(wx.getStorageSync('openid'))
            console.log(res)
          })

        },

        fail: function () {
          wx.showModal({
            title: '授权用户才能停车'
          })
        }
      })
    },
  }
})