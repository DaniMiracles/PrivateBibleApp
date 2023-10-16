package com.example.privatebibleapp.core

import android.content.Context
import android.util.AttributeSet
import com.example.privatebibleapp.R

class CustomTextView : androidx.appcompat.widget.AppCompatTextView, TextMapper {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion
    override fun map(data: String) = setText(data)
}

interface TextMapper : Abstract.Mapper.Data<String, Unit>

class CollapseView : androidx.appcompat.widget.AppCompatImageView, CollapseMapper {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion
    override fun map(data: Boolean) {
        val iconId: Int = if (data) {
            R.drawable.expand_more_px
        } else {
            R.drawable.expand_less_px
        }
        setImageResource(iconId)
    }
}

interface CollapseMapper : Abstract.Mapper.Data<Boolean, Unit>{
}