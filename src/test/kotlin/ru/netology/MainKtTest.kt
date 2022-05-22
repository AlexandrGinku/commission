package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun transfer_commission() {
        // arrange
        val cardLimitPerDay  = 0
        val cardLimitPerMonth = 0
        val cardType = "Vk Pay"
        val transferAmount = 1000
        val amount = 1000
        val expectedCommission = "Комиссия 0"
        // act
        val actualCommission = transfer(cardLimitPerDay, cardLimitPerMonth, cardType, transferAmount, amount)
        // assert
        assertEquals(expectedCommission,actualCommission)
    }

    @Test
    fun transfer_card_limit_per_day() {
        // arrange
        val cardLimitPerDay  = 150001
        val cardLimitPerMonth = 0
        val cardType = "Vk Pay"
        val transferAmount = 1000
        val amount = 1000
        val expectedCommission = "Превышен лимит перевода в сутки"
        // act
        val actualCommission = transfer(cardLimitPerDay, cardLimitPerMonth, cardType, transferAmount, amount)
        // assert
        assertEquals(expectedCommission,actualCommission)
    }

    @Test
    fun transfer_card_limit_per_month() {
        // arrange
        val cardLimitPerDay  = 1500
        val cardLimitPerMonth = 600001
        val cardType = "Vk Pay"
        val transferAmount = 1000
        val amount = 1000
        val expectedCommission = "Превышен лимит перевода в месяц"
        // act
        val actualCommission = transfer(cardLimitPerDay, cardLimitPerMonth, cardType, transferAmount, amount)
        // assert
        assertEquals(expectedCommission,actualCommission)
    }

    @Test
    fun commission_result_vk_pay() {
        // arrange
        val cardType = "Vk Pay"
        val transferAmount = 1000
        val amount = 1000
        val expectedCommission = "Комиссия 0"
        // act
        val actualCommission = commission(cardType, transferAmount, amount)
        // assert
        assertEquals(expectedCommission,actualCommission)
    }

    @Test
    fun commission_result_maestro() {
        // arrange
        val cardType = "Maestro"
        val transferAmount = 75001
        val amount = 1000
        val expectedCommission = "Комиссия 2600 копеек"
        // act
        val actualCommission = commission(cardType, transferAmount, amount)
        // assert
        assertEquals(expectedCommission,actualCommission)
    }

    @Test
    fun commission_result_master() {
        // arrange
        val cardType = "MasterCard"
        val transferAmount = 75001
        val amount = 1000
        val expectedCommission = "Комиссия 2600 копеек"
        // act
        val actualCommission = commission(cardType, transferAmount, amount)
        // assert
        assertEquals(expectedCommission,actualCommission)
    }

    @Test
    fun commission_result_mir() {
        // arrange
        val cardType = "Мир"
        val transferAmount = 75001
        val amount = 1000
        val expectedCommission = "Комиссия 3500 копеек"
        // act
        val actualCommission = commission(cardType, transferAmount, amount)
        // assert
        assertEquals(expectedCommission,actualCommission)
    }

    @Test
    fun commission_result_visa() {
        // arrange
        val cardType = "Visa"
        val transferAmount = 75001
        val amount = 1000
        val expectedCommission = "Комиссия 3500 копеек"
        // act
        val actualCommission = commission(cardType, transferAmount, amount)
        // assert
        assertEquals(expectedCommission,actualCommission)
    }

    @Test
    fun cardMasterCardMaestro_commission_0() {
        // arrange
        val transferAmount = 1000
        val amount = 1000
        val expectedCommission = 0
        // act
        val actualCommission = cardMasterCardMaestro(transferAmount, amount)
        // assert
        assertEquals(expectedCommission,actualCommission)
    }

    @Test
    fun cardMasterCardMaestro_commission_over_the_limit() {
        // arrange
        val transferAmount = 75001
        val amount = 1000
        val expectedCommission = 2600
        // act
        val actualCommission = cardMasterCardMaestro(transferAmount, amount)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }

        @Test
    fun cardVisaMir_min_commission() {
            // arrange
            val amount = 1000
            val expectedCommission = 3500
            // act
            val actualCommission = cardVisaMir(amount)
            // assert
            assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun cardVisaMir_min_commission_over_the_limit() {
        // arrange
        val amount = 10000
        val expectedCommission = 7500
        // act
        val actualCommission = cardVisaMir(amount)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun vkPay_commission_0() {
        // arrange
        val transferAmount = 0
        val amount = 1000
        val vkPayTimeLimitMonth = 40_000
        val vkPayOneTimeLimit = 15_000
        val expectedCommission = "Комиссия 0"
        // act
        val actualCommission = vkPay(transferAmount, amount, vkPayTimeLimitMonth, vkPayOneTimeLimit)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun vkPay_pay_one_time_limit() {
        // arrange
        val transferAmount = 0
        val amount = 15001
        val vkPayTimeLimitMonth = 40_000
        val vkPayOneTimeLimit = 15_000
        val expectedCommission = "Превышен лит перевода за один раз"
        // act
        val actualCommission = vkPay(transferAmount, amount, vkPayTimeLimitMonth, vkPayOneTimeLimit)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }

    @Test
    fun vkPay_pay_time_limit_month() {
        // arrange
        val transferAmount = 40001
        val amount = 1000
        val vkPayTimeLimitMonth = 40_000
        val vkPayOneTimeLimit = 15_000
        val expectedCommission = "Превышен лит перевода месяц"
        // act
        val actualCommission = vkPay(transferAmount, amount, vkPayTimeLimitMonth, vkPayOneTimeLimit)
        // assert
        assertEquals(expectedCommission, actualCommission)
    }
}