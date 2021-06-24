// 云函数入口文件
const cloud = require('wx-server-sdk')
const TcbRouter = require('tcb-router')
const rp = require('request-promise')
const BASE_URL = 'http://193.112.203.154:8080/parkingarea/order'
cloud.init()

// 云函数入口函数
exports.main = async (event, context) => {
  const wxContext = cloud.getWXContext()
  const app = new TcbRouter({
    event
  })
  app.router('personal',async(ctx,next) => {
    ctx.body = await rp(BASE_URL + `/personal?openid=${wxContext.OPENID}`).then((res)=>{
      return JSON.parse(res)
    })
  })

  app.router('detail',async(ctx,next) => {
    ctx.body = await rp(BASE_URL + `/detail?orderId=${event.orderId}`).then((res)=>{
      return JSON.parse(res)
    })
  })

  app.router('create',async(ctx,next) => {
    const licensePlateNumber = encodeURI(event.licensePlateNumber)
    var options = {
      method: 'post',
      uri: BASE_URL + `/create?licensePlateNumber=${licensePlateNumber}&parkingId=${event.parkingId}`,
    };
    ctx.body = await rp(options).then((res)=>{
      return JSON.parse(res)
    })
  })

  app.router('pay',async(ctx,next) => {
    var options = {
      method: 'post',
      uri: BASE_URL + `/pay?orderId=${event.orderId}`,
    };
    ctx.body = await rp(options).then((res)=>{
      return JSON.parse(res)
    })
  })
  return app.serve()
}