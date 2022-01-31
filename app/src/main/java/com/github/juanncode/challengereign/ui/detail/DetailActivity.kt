package com.github.juanncode.challengereign.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.github.juanncode.challengereign.R
import com.github.juanncode.challengereign.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val URL = "URL"
    }

    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.webview.settings.javaScriptEnabled = true
        intent.extras?.getString(URL)?.let { url ->
            if (url.isEmpty()) {
                binding.progressBar.visibility = View.GONE
                showAlert()
            } else {
                binding.webview.loadUrl(url)
            }
        }

        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.toolbar.setNavigationOnClickListener { finish() }

    }

    private fun showAlert() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.error_webview_empty)
            .setTitle(R.string.app_name)
        builder.apply {
            setPositiveButton(R.string.Ok) { _, _ -> finish() }

        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}