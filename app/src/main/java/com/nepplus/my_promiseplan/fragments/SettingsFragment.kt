package com.nepplus.my_promiseplan.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nepplus.my_promiseplan.R
import com.nepplus.my_promiseplan.databinding.FragmentInvitedAppointmentsBinding
import com.nepplus.my_promiseplan.databinding.FragmentSettingBinding
import com.nepplus.my_promiseplan.dialogs.CustomAlertDialog
import com.nepplus.my_promiseplan.main.LoginActivity
import com.nepplus.my_promiseplan.utils.ContextUtil
import com.nepplus.my_promiseplan.utils.GlobalData

class SettingsFragment : BaseFragment() {

    lateinit var binding : FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvents()
        setValues()
    }

    override fun setEvents() {
        binding.profileImg.setOnClickListener {

        }

        binding.changeNickLayout.setOnClickListener {

        }

        binding.changePwLayout.setOnClickListener {

        }

        binding.myWorkLayout.setOnClickListener {

        }

        binding.myFriendsLayout.setOnClickListener {

        }

        binding.logoutLayout.setOnClickListener {
            val alert = CustomAlertDialog(mContext,requireActivity())
            alert.myDialog()

            alert.binding.titleTxt.text = "로그아웃"
            alert.binding.bodytxt.text = "정말 로그아웃 하시겠습니까?"
            alert.binding.contentEdt.visibility = View.GONE
            alert.binding.positiveBtn.setOnClickListener {

                ContextUtil.clear(mContext)

                GlobalData.loginUser = null

                val myIntent = Intent(mContext, LoginActivity::class.java)
                myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(myIntent)

                alert.dialog.dismiss()

            }

            alert.binding.negativeBtn.setOnClickListener {
                alert.dialog.dismiss()
            }
        }

    }

    override fun setValues() {

    }

}