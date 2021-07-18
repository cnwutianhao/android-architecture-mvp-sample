package com.tyhoo.mvp.kotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tyhoo.mvp.kotlin.R
import com.tyhoo.mvp.kotlin.data.User

class MainFragment : Fragment(), MainContract.View {

    private lateinit var userText: TextView
    private lateinit var userPb: ProgressBar
    private lateinit var parseJsonBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        with(view) {
            userText = findViewById(R.id.user_text)
            userPb = findViewById(R.id.user_pb)
            parseJsonBtn = findViewById(R.id.parse_json_btn)
        }

        parseJsonBtn.setOnClickListener {
            Log.d(TAG, "Parse Json.")
            presenter.parseJson()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override lateinit var presenter: MainContract.Presenter

    override fun setLoadingIndicator(active: Boolean) {
        if (active) {
            userPb.visibility = View.VISIBLE
        } else {
            userPb.visibility = View.GONE
        }
    }

    override fun showUser(user: String) {
        userText.visibility = View.VISIBLE
        userText.text = user
    }

    override fun hideUser() {
        userText.visibility = View.GONE
    }

    override fun showParseJson(user: User) {
        val text = getString(
            R.string.parse_text, user.location, user.html_url, user.name,
            user.company, user.location
        )
        userText.text = text
    }

    companion object {
        const val TAG = "MainFragment"

        fun newInstance() = MainFragment().apply {
        }
    }
}