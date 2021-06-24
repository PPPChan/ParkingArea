// pages/test1/test1.js
Page({
  data: {
    territory: "粤",
    plateNo: "",
  },

  //开启 地域选择键盘
  open: function () { this.setData({ isShow: true, keyBoardType: 1}); },

  //开启 车牌选择键盘
  openInput: function () { this.setData({ isShow: true, keyBoardType: 2 ,css:"unit-actived"});},

  //关闭键盘
  close: function () {  this.setData({ isShow: false });},

  //点击了删除
  delete: function (e) {
    this.setData({ plateNo: this.data.plateNo.substring(0, this.data.plateNo.length - 1) });
  },

  //点击键盘
  click: function (e) {
    var val = e.detail;
    if (!val) return;

    //汉字 正则表达式
    var reg = new RegExp('[u4E00-u9FFF]+', 'g');
    if (!reg.test(val)) {
      console.log(val)
      this.setData({ territory: val, keyBoardType: 2 });
    } else {
      console.log("2"+val)
      if (this.data.plateNo.length == 6) return;
      this.setData({ plateNo: this.data.plateNo + val });
    }
  },

  //点击了 小键盘确认
  ok: function (e) { this.setData({ isShow: false }); },

  //单击查询按钮
  select: function(){
    if(this.data.plateNo.length<6){
      wx.showToast({
        title: `请输入正确的车牌号`,
        icon: 'none',
      });
    }
    else{
      wx.showLoading({
        title: '绑定中',
      })
      wx.cloud.callFunction({
        name: 'licenseplate',
        data: {
          $url: "bind",
          licensePlateNumber: this.data.territory+this.data.plateNo,
        }
      }).then(async(res)=>{
        const result = res.result
        console.log(result)
        wx.hideLoading()
        if(result.code == "0"){
          wx.showToast({
            title: `绑定车牌号成功,车牌号：${result.data}`,
            icon: 'none',
          });
        }else{
          wx.showToast({
            title: result.msg,
            icon: 'none',
          });
        }
        setTimeout(()=>{
          wx.navigateBack({
          })
        },1500)
      })
      
    }
    
  },
  
})