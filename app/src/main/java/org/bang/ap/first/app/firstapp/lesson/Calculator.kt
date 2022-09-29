package org.bang.ap.first.app.firstapp.lesson

import kotlin.system.exitProcess

// 依次四则运算表达式计算器
fun main() {
    while (true) {
        println("====请输入你的表达式====")
        val input = readLine()
        try {
            input?.let { it ->
                val ret = calculate(it)
                // 1+1=2
                println("$input=$ret")
                println("是否继续使用(y/n)")
                val cmd = readLine()
                cmd?.let {
                    if (it == "n") {
                        exitProcess(-1)
                    } else {
                        // 继续使用程序
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
    // 运行到这里的时候，程序就结束了
}

fun calculate(input: String): String {
    return if (input.contains("+")) {
        val nums = input.trim().split("+")
        operate(nums[0].toDouble(), nums[1].toDouble(), "+").toString()
    } else if (input.contains("-")) {
        val nums = input.trim().split("-")
        operate(nums[0].toDouble(), nums[1].toDouble(), "-").toString()
    } else if (input.contains("*")) {
        val nums = input.trim().split("*")
        operate(nums[0].toDouble(), nums[1].toDouble(), "*").toString()
    } else if (input.contains("/")) {
        val nums = input.trim().split("/")
        operate(nums[0].toDouble(), nums[1].toDouble(), "/").toString()
    } else {
        "error: 你输入的表达式有误"
    }
}

fun operate(num: Double, num2: Double, operate: String): Double {
    return when (operate) {
        "+" -> num + num2
        "-" -> num - num2
        "*" -> num * num2
        "/" -> num / num2
        else -> 0.0
    }
}
