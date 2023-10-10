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
    @SerializedName("m_size")
    val mSize: String? = "",
    @SerializedName("oppo_drafts")
    val oppoDrafts: String? = "",
    @SerializedName("oppo_mass")
    val oppoMass: String? = "",
    @SerializedName("oppo_timer")
    val oppoTimer: String? = "",
    @SerializedName("oppo_groupaddress")
    val oppoGroupAddress: String? = "",
    @SerializedName("oppo_collected")
    val oppoCollected: String? = "",
    @SerializedName("oppo_sub_date")
    val oppoSubDate: String? = "",
    @SerializedName("oppo_service_message_sms_type")
    val oppoServiceMessageSmsType: String? = "",
    @SerializedName("bubble")
    val bubble: String? = "",
    @SerializedName("deleted")
    val deleted: String? = "",
    @SerializedName("sync_state")
    val syncState: String? = "",
    @SerializedName("sync_id")
    val syncId: String? = "",
    @SerializedName("oppo_message_url")
    val oppoMessageUrl: String? = "",
    @SerializedName("oppo_sms_type")
    val oppoSmsType: String? = "",
    @SerializedName("block_type")
    val blockType: String? = "",
    @SerializedName("favourite")
    val favourite: String? = "",
    @SerializedName("rcs_message_id")
    val rcsMessageId: String? = "",
    @SerializedName("rcs_file_name")
    val rcsFileName: String? = "",
    @SerializedName("rcs_mime_type")
    val rcsMimeType: String? = "",
    @SerializedName("rcs_msg_type")
    val rcsMsgType: String? = "",
    @SerializedName("rcs_msg_state")
    val rcsMsgState: String? = "",
    @SerializedName("rcs_chat_type")
    val rcsChatType: String? = "",
    @SerializedName("rcs_conversation_id")
    val rcsConversationId: String? = "",
    @SerializedName("rcs_contribution_id")
    val rcsContributionId: String? = "",
    @SerializedName("rcs_file_selector")
    val rcsFileSelector: String? = "",
    @SerializedName("rcs_file_transfered")
    val rcsFileTransfered: String? = "",
    @SerializedName("rcs_file_transfer_id")
    val rcsFileTransferId: String? = "",
    @SerializedName("rcs_file_icon")
    val rcsFileIcon: String? = "",
    @SerializedName("rcs_burn")
    val rcsBurn: String? = "",
    @SerializedName("rcs_header")
    val rcsHeader: String? = "",
    @SerializedName("rcs_file_path")
    val rcsFilePath: String? = "",
    @SerializedName("rcs_is_download")
    val rcsIsDownload: String? = "",
    @SerializedName("rcs_file_size")
    val rcsFileSize: String? = "",
    @SerializedName("rcs_thumb_path")
    val rcsThumbPath: String? = "",
    @SerializedName("rcs_extend_body")
    val rcsExtendBody: String? = "",
    @SerializedName("rcs_media_played")
    val rcsMediaPlayed: String? = "",
    @SerializedName("rcs_ext_contact")
    val rcsExtContact: String? = "",
    @SerializedName("rcs_file_record")
    val rcsFileRecord: String? = "",
    @SerializedName("rcs_transfer_date")
    val rcsTransferDate: String? = "",
    @SerializedName("rcs_group_at_reminds")
    val rcsGroupAtReminds: String? = "",
    @SerializedName("rcs_audio_read")
    val rcsAudioRead: String? = "",
) {

    var isCreditSms = false
    var isDebitSms = false
    var isCreditDebitSms = false

    fun a() {
        Gson()
    }

}
