// 云函数入口文件
const cloud = require('wx-server-sdk')

cloud.init()

const db = cloud.database()

const rp = require('request-promise')

const URL = "http://193.112.203.154:8080/parkingarea/userinfo/create"

const openidCollection = db.collection('openid')



// 云函数入口函数
exports.main = async (event, context) => {
  const wxContext = cloud.getWXContext()
  var row = await openidCollection.where({ openid: wxContext.OPENID }).get()
  console.log("row:"+row)
  if (row.data.length == 0) {
    openidCollection.add({
      data: {
        openid: wxContext.OPENID,
      }
    }).then((res) => {

    }).catch(console.error)
    //首次登录注册用户
    const nickName = encodeURI(event.nickName)
    var options = {
      method: 'post',
      uri: URL + `?openid=${wxContext.OPENID}&userName=${nickName}&phone=`,
      json: true
    };
    const user = await rp(options)
    console.log("user:" + user)
  }
  return {
    row: row,
    event,
    openid: wxContext.OPENID,
    appid: wxContext.APPID,
    unionid: wxContext.UNIONID,
  }

  // db.collection('todos').doc('todo-identifiant-aleatoire').get({
  //   success: function(res) {
  //     // res.data 包含该记录的数据
  //     console.log(res.data)
  //   }
  // })
}