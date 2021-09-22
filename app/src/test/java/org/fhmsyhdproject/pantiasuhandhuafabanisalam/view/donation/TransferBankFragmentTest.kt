package org.fhmsyhdproject.pantiasuhandhuafabanisalam.view.donation

import org.junit.Test

import org.junit.Assert.*

class TransferBankFragmentTest {

    val noRek = "23454tgfe4"

    @Test
    fun copyText() {
        val result = copyTextTest(noRek)
        assertEquals(result, "23454tgfe4")
    }
}