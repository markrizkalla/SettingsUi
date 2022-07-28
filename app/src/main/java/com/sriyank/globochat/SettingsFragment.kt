package com.sriyank.globochat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.*
import java.util.prefs.Preferences


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val accSettingPref = findPreference<Preference>(getString(R.string.key_account_settings))

        accSettingPref?.setOnPreferenceClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
            val navController = navHostFragment.navController
            val action = SettingsFragmentDirections.actionSettingsToAccSettings()
            navController.navigate(action)
            true

        }

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        val autoReplyTime = sharedPreference.getString(getString(R.string.key_auto_reply_time), "")

        val statusPref = findPreference<EditTextPreference>(getString(R.string.key_status))
        statusPref?.setOnPreferenceChangeListener { preference, newValue ->

            val newStatus = newValue as String
            if(newStatus.contains("bad")){
                Toast.makeText(context,"Can not",Toast.LENGTH_SHORT).show()
                false
            }else{
                true
            }
        }

        val notificationPre = findPreference<SwitchPreferenceCompat>(getString(R.string.key_new_msg_notif))
        notificationPre?.summaryProvider = Preference.SummaryProvider<SwitchPreferenceCompat> {

            if (it.isChecked){
                "Status : On"
            }else{
                "Status Off"
            }
        }


    }

}