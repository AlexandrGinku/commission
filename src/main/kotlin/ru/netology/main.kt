package ru.netology

fun main() {
    val cardLimitPerDay = 150_000
    val cardLimitPerMonth = 600_000
    val cardType = "Maestro"
    val transferAmount = 80000
    val amount = 15000
    val result = transfer(cardLimitPerDay, cardLimitPerMonth, cardType, transferAmount, amount)
    println(result)


}

fun transfer(
    cardLimitPerDay: Int = 0,
    cardLimitPerMonth: Int = 0,
    cardType: String = "Vk Pay",
    transferAmount: Int = 0,
    amount: Int = 0
): String {
    val cardLimitPerMonthLocal = 600_000
    val cardLimitPerDayLocal = 150_000
    return when {
        cardLimitPerMonth > cardLimitPerMonthLocal -> "Превышен лимит перевода в месяц"
        cardLimitPerDay > cardLimitPerDayLocal -> "Превышен лимит перевода в сутки"
        else -> commission(cardType, transferAmount, amount)
    }
}

fun commission(cardType: String = "Vk Pay", transferAmount: Int = 0, amount: Int = 0): String {
    return when (cardType) {
        "MasterCard", "Maestro" -> "Комиссия ${cardMasterCardMaestro(transferAmount, amount)} копеек"
        "Visa", "Мир" -> "Комиссия ${cardVisaMir(amount)} копеек"
        else -> vkPay(transferAmount, amount)
    }
}

fun cardMasterCardMaestro(transferAmount: Int = 0, amount: Int): Int {
    val transferAmountLocal = transferAmount * 100
    val amountLocal = amount * 100
    return when (transferAmountLocal) {
        in 0..7500000 -> 0
        else -> ((amountLocal / 1000) * 6) + 2000
    }
}

fun cardVisaMir(amount: Int): Int {
    val amountLocal = amount * 100
    val minCommission = 3_500
    val fixCommission = (amountLocal / 10_000) * 75
    return if (fixCommission < minCommission) minCommission else fixCommission
}

fun vkPay(
    transferAmount: Int = 0,
    amount: Int,
    vkPayTimeLimitMonth: Int = 40_000,
    vkPayOneTimeLimit: Int = 15_000
): String {
    return when {
        transferAmount > vkPayTimeLimitMonth -> "Превышен лимит перевода в месяц"
        amount > vkPayOneTimeLimit -> "Превышен лимит перевода за один раз"
        else -> "Комиссия 0"
    }
}
