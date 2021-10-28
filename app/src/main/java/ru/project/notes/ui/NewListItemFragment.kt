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
    private lateinit var titleEditText: EditText
    private lateinit var detailEditText: EditText
    private lateinit var saveButton: Button
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

        // Так преобразовала студия

//        saveButton.setOnClickListener { view1: View? ->
//            val noteEntityNew = NoteEntity(titleEditText.text.toString(), detailEditText.text.toString())
//
//            onFragmentClickHandler!!.onClickButtonSaveNewListItemFragment(noteEntityNew)
//        }

        // Способ слушателя аналогичный Java

//        saveButton.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(p0: View?) {
//                val noteEntityNew = NoteEntity(titleEditText.text.toString(), detailEditText.text.toString())
//                onFragmentClickHandler!!.onClickButtonSaveNewListItemFragment(noteEntityNew)
//            }
//        })

        // Способ с лямбдой

        saveButton.setOnClickListener { v ->
            val noteEntityNew = NoteEntity(titleEditText.text.toString(), detailEditText.text.toString())
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