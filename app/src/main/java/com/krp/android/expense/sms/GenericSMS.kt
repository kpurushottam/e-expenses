package com.krp.android.expense.sms

import android.provider.Telephony
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class GenericSMS(
    @SerializedName(Telephony.Sms._ID)
    val id: String? = "",
    @SerializedName("thread_id")
    val threadId: String? = "",
    @SerializedName(Telephony.Sms.ADDRESS)
    val address: String? = "",
    @SerializedName(Telephony.Sms.PERSON)
    val person: String? = "",
    @SerializedName(Telephony.Sms.DATE)
    val date: String? = "",
    @SerializedName(Telephony.Sms.DATE_SENT)
    val dateSent: String? = "",
    @SerializedName(Telephony.Sms.PROTOCOL)
    val protocol: String? = "",
    @SerializedName(Telephony.Sms.READ)
    val read: String? = "",
    @SerializedName(Telephony.Sms.STATUS)
    val status: String? = "",
    @SerializedName(Telephony.Sms.TYPE)
    val type: String? = "",
    @SerializedName(Telephony.Sms.REPLY_PATH_PRESENT)
    val replyPathPresent: String? = "",
    @SerializedName(Telephony.Sms.SUBJECT)
    val subject: String? = "",
    @SerializedName(Telephony.Sms.BODY)
    val body: String? = "",
    @SerializedName(Telephony.Sms.SERVICE_CENTER)
    val serviceCenter: String? = "",
    @SerializedName(Telephony.Sms.LOCKED)
    val locked: String? = "",
    @SerializedName(Telephony.Sms.SUBSCRIPTION_ID)
    val subscriptionId: String? = "",
    @SerializedName("phone_id")
    val phoneId: String? = "",
    @SerializedName(Telephony.Sms.ERROR_CODE)
    val errorCode: String? = "",
    @SerializedName(Telephony.Sms.CREATOR)
    val creator: String? = "",
    @SerializedName(Telephony.Sms.SEEN)
    val seen: String? = "",
    @SerializedName("priority")
    val priority: String? = "",
    val mSize: String? = "",
    val oppoDrafts: String? = "",
    val oppoMass: String? = "",
    val oppoTimer: String? = "",
    val oppoGroupAddress: String? = "",
    val oppoCollect: String? = "",
    val oppoSubDate: String? = "",
    val oppoServiceMessageSmsType: String? = "",
    val bubble: String? = "",
    val deleted: String? = "",
    val syncState: String? = "",
    val syncId: String? = "",
    val oppoMessageUrl: String? = "",
    val oppoSmsType: String? = "",
    val blockType: String? = "",
    val favourite: String? = "",
    val rcsMessageId: String? = "",
    val rcsFileName: String? = "",
    val rcsMimeType: String? = "",
    val rcsMsgType: String? = "",
    val rcsMsgState: String? = "",
    val rcsChatType: String? = "",
    val rcsConversationId: String? = "",
    val rcsContributionId: String? = "",
    val rcsFileSelector: String? = "",
    val rcsFileTransfered: String? = "",
    val rcsFileTransferId: String? = "",
    val rcsFileIcon: String? = "",
    val rcsBurn: String? = "",
    val rcsHeader: String? = "",
    val rcsFilePath: String? = "",
    val rcsIsDownload: String? = "",
    val rcsFileSize: String? = "",
    val rcsThumbPath: String? = "",
    val rcsExtendBody: String? = "",
    val rcsMediaPlayed: String? = "",
    val rcsExtContact: String? = "",
    val rcsFileRecord: String? = "",
    val rcsTransferDate: String? = "",
    val rcsGroupAtReminds: String? = "",
    val rcsAudioRead: String? = "",
) {

    fun a() {
        Gson()
    }

}
