package com.github.fajaragungpramana.field

import android.content.Context
import android.os.Build
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class FieldInput @JvmOverloads constructor(
    private val ctx: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(ctx, attrs, defStyle, defStyleRes) {

    private lateinit var mTextInputLayout: TextInputLayout
    private lateinit var mTextInputEditText: TextInputEditText
    private lateinit var mDrawableEnd: ImageView

    private var mView: View? = null
        set(value) {
            field = value

            field?.let {
                mTextInputLayout = it.findViewById(R.id.til_field_input)
                mTextInputEditText = it.findViewById(R.id.tie_field_input)
                mDrawableEnd = it.findViewById(R.id.iv_field_drawable_end)
            }
        }

    var drawableEnd: Int = 0
        set(value) {
            field = value

            if (field != 0) mDrawableEnd.setImageDrawable(ContextCompat.getDrawable(ctx, field))
        }

    var errorMessage: String? = null
        set(value) {
            field = value

            mTextInputLayout.errorIconDrawable = null
            if (mTextInputLayout.isErrorEnabled) mTextInputLayout.error = field
        }

    var focus: Boolean = true
        set(value) {
            field = value

            mTextInputEditText.isFocusable = focus
        }

    var hint: String? = null
        set(value) {
            field = value

            mTextInputEditText.hint = hint
            mTextInputEditText.setOnFocusChangeListener { _, hasFocus ->
                mTextInputEditText.hint = if (hasFocus) null else hint
                mTextInputLayout.hint = if (hasFocus) hint else null
            }
        }

    var inputType: Int = 0
        set(value) {
            field = value

            mTextInputEditText.inputType = field
        }

    @Suppress("DEPRECATION")
    var passwordToggleEnabled: Boolean = false
        set(value) {
            field = value

            mTextInputLayout.isPasswordVisibilityToggleEnabled = field
        }

    @Suppress("DEPRECATION")
    var style: Int = 0
        set(value) {
            field = value

            if (field != 0)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    mTextInputEditText.setTextAppearance(field)
                else
                    mTextInputEditText.setTextAppearance(ctx, field)
        }

    var text: String? = null
        get() = mTextInputEditText.text.toString()
        set(value) {
            field = value

            if (!field.isNullOrEmpty()) mTextInputLayout.hint = hint
            mTextInputEditText.setText(field)
        }

    var textAllCaps: Boolean = false
        set(value) {
            field = value

            mTextInputEditText.isAllCaps = field
        }

    var textColor: Int = 0
        set(value) {
            field = value

            if (field != 0) mTextInputEditText.setTextColor(ContextCompat.getColor(ctx, field))
        }

    var textSize: Float = 0F
        set(value) {
            field = value

            if (field != 0F) mTextInputEditText.textSize = field - 50F
        }

    init {
        mView = LayoutInflater.from(ctx).inflate(R.layout.field_input, this, true)

        val ta = ctx.obtainStyledAttributes(attrs, R.styleable.FieldInput, defStyle, defStyleRes)

        drawableEnd = ta.getResourceId(R.styleable.FieldInput_drawableEnd, 0)
        focus = ta.getBoolean(R.styleable.FieldInput_focusable, true)
        hint = ta.getString(R.styleable.FieldInput_hint)
        inputType = ta.getInt(R.styleable.FieldInput_inputType, InputType.TYPE_CLASS_TEXT)
        passwordToggleEnabled = ta.getBoolean(R.styleable.FieldInput_passwordToggleEnabled, false)
        style = ta.getResourceId(R.styleable.FieldInput_style, 0)
        textAllCaps = ta.getBoolean(R.styleable.FieldInput_textAllCaps, false)
        textColor = ta.getResourceId(R.styleable.FieldInput_textColor, 0)
        textSize = ta.getDimension(R.styleable.FieldInput_textSize, 0F)

        ta.recycle()
    }

    fun setOnClickDrawableListener(
        drawablePosition: DrawablePosition,
        onClickDrawableListener: () -> Unit
    ) {
        when (drawablePosition) {
            DrawablePosition.START -> {
            }

            DrawablePosition.END -> mDrawableEnd.setOnClickListener { onClickDrawableListener() }
        }
    }

    override fun onDetachedFromWindow() {
        mView = null
        super.onDetachedFromWindow()
    }

}