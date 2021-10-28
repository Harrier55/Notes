package ru.project.notes.ui

import android.os.Bundle
import ru.project.notes.ui.ProfileFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.project.notes.R

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
           return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}