Component({

  // externalClasses: ['v-panel'],

  properties: {
    isShow: {
      type: Boolean,
      value: true,
    },
    buttonBorder: {
      type: String,
      value: "1px solid #ccc"
    },
    backgroundColor: {
      type: String,
      value: "#fff"
    },
    //1为省份键盘，其它为英文键盘
    keyBoardType: {
      type: Number,
      value: 1,
    }
  },
  data: {
    keyVehicle1: '京沪浙苏粤鲁晋冀',
    keyVehicle2: '豫川渝辽吉黑皖鄂',
    keyVehicle3: '湘赣闽陕甘宁蒙津',
    keyVehicle4: '贵云桂琼青新藏',
    keyNumber: '1234567890',
    keyEnInput1: 'QWERTYUIOP',
    keyEnInput2: 'ASDFGHJKL',
    keyEnInput3: 'ZXCVBNM',
  },
  methods: {
    vehicleTap: function (event) {
      let val = event.target.dataset.value;
      switch (val) {
        case 'delete':
          this.triggerEvent('delete');
          this.triggerEvent('inputchange');
          break;
        case 'ok':
          this.triggerEvent('ok');
          break;
        default:
          this.triggerEvent('inputchange', val);
      }
    },
  }
});