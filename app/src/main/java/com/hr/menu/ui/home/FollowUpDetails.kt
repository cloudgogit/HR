package com.hr.menu.ui.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class FollowUpDetails( var enquiry_id: String,
                            var followup_date: String,
                            var problem: String,
                            var customer_description: String,
                            var customer_name: String,
                            var mobileno: String,
                            var mobileno_alt: String,
                            var address: String
                            ):Parcelable