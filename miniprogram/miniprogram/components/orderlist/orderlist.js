// components/orderlist/orderlist.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    orderList: Array
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
    goToOrderDetail(event) {
      
      const ds = event.currentTarget.dataset
      const orderid = ds.orderid
      console.log(ds)
      console.log(orderid)
      wx.navigateTo({
        url: `../../pages/orderDetail/orderDetail?orderId=${orderid}`,
      })
    },
  }
})
