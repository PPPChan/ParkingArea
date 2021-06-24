// 云函数入口文件
const cloud = require('wx-server-sdk')
const TcbRouter = require('tcb-router')
const rp = require('request-promise')
const BASE_URL = 'http://193.112.203.154:8080/parkingarea/licenseplate'
cloud.init()

// 云函数入口函数
exports.main = async (event, context) => {
  const wxContext = cloud.getWXContext()
  const app = new TcbRouter({
    event
  })
  

  app.router('bind',async(ctx,next) => {
    const licensePlateNumber = encodeURI(event.licensePlateNumber)
    var options = {
      method: 'post',
      uri: BASE_URL + `/bind?openid=${wxContext.OPENID}&licensePlateNumber=${licensePlateNumber}`,
    };
    ctx.body = await rp(options).then((res)=>{
      return JSON.parse(res)
    })
  })


  app.router('unbind',async(ctx,next) => {
    const licensePlateNumber = encodeURI(event.licensePlateNumber)
    var options = {
      method: 'post',
      uri: BASE_URL + `/unbind?openid=${wxContext.OPENID}&licensePlateNumber=${licensePlateNumber}`,
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