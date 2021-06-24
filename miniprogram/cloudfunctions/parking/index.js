const cloud = require('wx-server-sdk')
const TcbRouter = require('tcb-router')
const rp = require('request-promise')
const BASE_URL = 'http://193.112.203.154:8080/parkingarea/parking'
cloud.init()

// 云函数入口函数
exports.main = async (event, context) => {
  const wxContext = cloud.getWXContext()
  const app = new TcbRouter({
    event
  })
  

  app.router('list',async(ctx,next) => {
    var options = {
      method: 'get',
      uri: BASE_URL + `/list`,
    };
    ctx.body = await rp(options).then((res)=>{
      return JSON.parse(res)
    })
  })


  app.router('detail',async(ctx,next) => {
    var options = {
      method: 'get',
      uri: BASE_URL + `/detail?parkingId=${event.parkingId}`,
    };
    ctx.body = await rp(options).then((res)=>{
      return JSON.parse(res)
    })
  })

  app.router('personal',async(ctx,next) => {
    var options = {
      method: 'get',
      uri: BASE_URL + `/personal?openid=${wxContext.OPENID}`,
    };
    ctx.body = await rp(options).then((res)=>{
      return JSON.parse(res)
    })
  })
  return app.serve()
}