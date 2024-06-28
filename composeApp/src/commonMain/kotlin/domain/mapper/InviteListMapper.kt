package domain.mapper

import domain.mapper.base.ResultMapper
import domain.model.BoldTypeCustomerInfo
import domain.model.InviteCellDom
import domain.model.InviteListDom
import domain.model.MapKey
import domain.model.NavigationDom
import domain.model.StatusInvite
import network.data.InviteItemResponse
import network.data.InviteResponse
import network.data.NavigationResponse

class InviteListMapper constructor(
) : ResultMapper<InviteResponse, InviteListDom>() {
    override fun mapSuccessResult(src: InviteResponse?): InviteListDom {
        return InviteListDom(
            inviteCodes = src?.inviteCodes?.map {
                mapInvite(it)
            } ?: emptyList(),
            navigation = mapNavigation(src?.navigation),
            statuses = listOf(MapKey("", "Все")).plus(src?.statuses?.map {
                MapKey(
                    it.key,
                    it.value
                )
            } ?: emptyList()),
        )
    }

    fun mapNavigation(it: NavigationResponse?): NavigationDom {
        return NavigationDom(
            nextPage = it?.nextPage?.toIntOrNull() ?: 0,
            prevPage = it?.prevPage?.toIntOrNull() ?: 0,
            limit = it?.limit?.toIntOrNull() ?: 0,
            recordCount = it?.recordCount?.toIntOrNull() ?: 0,
            currentPage = it?.currentPage?.toIntOrNull() ?: 0,
            pageCount = it?.pageCount?.toIntOrNull() ?: 0,
        )
    }

    private fun splitStringWithPhoneNumber(input: String): Array<BoldTypeCustomerInfo> {
        if (input.isEmpty()) return arrayOf()
        val regex = Regex("\\+7 \\(\\d{3}\\) \\*{4} \\d{3}")
        val matchResult = regex.find(input)
        val phoneNumber = matchResult?.value ?: ""

        val parts = input.split(phoneNumber)

        return arrayOf(
            BoldTypeCustomerInfo(false, parts[0]),
            BoldTypeCustomerInfo(true, phoneNumber),
            BoldTypeCustomerInfo(false, parts[1])
        )
    }

    fun mapInvite(it: InviteItemResponse): InviteCellDom {
        val customersInfo = splitStringWithPhoneNumber(it.customerInfo ?: "")
        val timeLeft =
            (it.statusTime?.day?.toIntOrNull() ?: 0) * 24 + (it.statusTime?.hours?.toIntOrNull()
                ?: 0)
        val maxHours = 3 * 24
        val timePassed = maxHours - timeLeft
        val procent = timePassed / ((maxHours + 0.1) / 100)
        val priceProcent = (it.currentIntTotal ?: 1) / (((it.maxTotal ?: 0) + 0.1) / 100)
        val stringBuilder = StringBuilder()
        stringBuilder.append("Осталось ")
        if (it.statusTime?.day == "0" && it.statusTime?.hours == "0" && it.statusTime?.minutes == "0") {
            stringBuilder.append("меньше минуты ")
        } else {
            if (it.statusTime?.day != "0")
                stringBuilder.append("${it.statusTime?.day} дня ")
            if (it.statusTime?.hours != "0")
                stringBuilder.append("${it.statusTime?.hours} ч ")
            stringBuilder.append("${it.statusTime?.minutes} мин")
        }
        val i = InviteCellDom(
            code = it.code ?: "",
            status = when (it.status?.lowercase() ?: "") {
                StatusInvite.ACTIVED.title -> StatusInvite.ACTIVED
                StatusInvite.WAIT.title -> StatusInvite.WAIT
                StatusInvite.EXPIRED.title -> StatusInvite.EXPIRED
                else -> StatusInvite.UNKNOW
            },
            dateTimeActiveToText = it.dateTimeActiveToText ?: "",
            dateTimeActivatedText = it.dateTimeActivatedText ?: "",
            dateTimeCreateText = it.dateTimeCreateText ?: "",
            maxTotal = it.maxTotal ?: 0,
            currentTotal = (it.currentTotal ?: 0).toString(),
            currentIntTotal = it.currentIntTotal ?: 0,
            customerInfo = customersInfo.toList(),//it.customerInfo ?: "",
            linkActivate = it.linkActivate ?: "",
            message = it.message ?: "",
            slider = procent.toInt() / 100f,
            statusTime = stringBuilder.toString(),
            sliderPrice = (priceProcent.toInt() + 1) / 100f
        )
        return i
    }

}