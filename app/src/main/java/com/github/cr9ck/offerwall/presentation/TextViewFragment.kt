package com.github.cr9ck.offerwall.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.cr9ck.offerwall.R
import kotlinx.android.synthetic.main.fragment_text_view.*

class TextViewFragment: Fragment(R.layout.fragment_text_view) {

    private val args: TextViewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.text = args.text
    }
}