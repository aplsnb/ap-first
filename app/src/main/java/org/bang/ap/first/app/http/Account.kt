package org.bang.ap.first.app.http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Account {
    var uid: String = ""

    var userName: String = "Freeman"

    var password: String = "password"

    var telNumber: String = "13000000000"

    override fun toString(): String {
        return "Account(uid='$uid', userName='$userName', password='$password', telNumber='$telNumber')"
    }
}

data class Account2(
    var uid: String,
    var userName: String,
    var password: String,
    var telNumber: String
)

fun main() {
    val gson = Gson()

    // 把json转换成对象
    val json =
        "{\"uid\":\"00001\",\"userName\":\"Freeman\",\"password\":\"password\",\"telNumber\":\"13000000000\"}"
    val account = gson.fromJson(json, Account2::class.java)
    println("json to object: $account")

    // 把对象转换成json
    val account2 = Account()
    val json2 = gson.toJson(account2)
    println("object to json: $json2")

    // 把json转换成集合
    val json3 =
        "[{\"uid\":\"00001\",\"userName\":\"Freeman\",\"password\":\"password\",\"telNumber\":\"13000000000\"}]"
    val accounts = gson.fromJson<List<Account>>(json3, object : TypeToken<List<Account>>() {}.type)
    println("json to list size: ${accounts.size}")

    // 把集合转换成json
    val json4 = gson.toJson(accounts)
    println("list to json: $json4")
}
