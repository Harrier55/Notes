package ru.project.notes.ui

import android.content.Context
import android.widget.EditText
import ru.project.notes.listener.OnFragmentClickHandler
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.project.notes.R
import ru.project.notes.Entity.NoteEntity
import java.lang.RuntimeException

class NewListItemFragment : Fragment() {
    private var titleEditText: EditText? = null
    private var detailEditText: EditText? = null
    private var saveButton: Button? = null
    private var onFragmentClickHandler: OnFragmentClickHandler? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentClickHandler = if (context is OnFragmentClickHandler) {
            context
        } else {
            throw RuntimeException("$context must implement OnFragmentClickHandler to NewListItemFrag")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_note_edit, container, false)
        initView(view)
        saveButton!!.setOnClickListener { view1: View? ->
            val title = titleEditText!!.text.toString()
            val detail = detailEditText!!.text.toString()
            val noteEntityNew = NoteEntity(title, detail)
            onFragmentClickHandler!!.onClickButtonSaveNewListItemFragment(noteEntityNew)
        }
        return view
    }

    private fun initView(view: View) {
        titleEditText = view.findViewById(R.id.title_edit_text)
        detailEditText = view.findViewById(R.id.detail_edit_text)
        saveButton = view.findViewById(R.id.save_button)
    }
}