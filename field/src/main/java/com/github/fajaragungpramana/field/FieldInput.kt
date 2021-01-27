package com.github.fajaragungpramana.field

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

/**
 * FieldInput
 *
 * FieldInput is layout component base on ViewGroup LinearLayout for get input from user.
 *
 * @property primaryColor = get application colorPrimary from theme.
 * @property mView = for initialize layout.
 * @property drawableEnd = for set icon in the right side of field input max width 24dp and height 24dp.
 * @property errorMessage = to set error message at the bottom of field input.
 * @property focus = set focus of field input. set true its mean user can type false user can't type.
 * @property hint = set field hint.
 * @property inputType = set input type of field.
 * @property style = set text appearance of field.
 * @property text = to get or set text of field.
 * @property textAllCaps = to set caps of field. set true is cap, set false is not.
 * @property textColor = to set text color of field.
 * @property textSize = to set text size of field.
 */
class FieldInput @JvmOverloads constructor(
    private val ctx: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(ctx, attrs, defStyle, defStyleRes) {

    private lateinit var mHint: TextView
    private lateinit var mFieldInput: EditText
    private lateinit var mError: TextView
    private lateinit var mDrawableEnd: ImageView

    private val primaryColor: Int
        get() {
            val typedValue = TypedValue()
            ctx.theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)

            return typedValue.data
        }

    private var mView: View? = null
        set(value) {
            field = value

            field?.let {
                mHint = it.findViewById(R.id.tv_field_hint)
                mFieldInput = it.findViewById(R.id.et_field_input)
                mError = it.findViewById(R.id.tv_field_error)
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

            mError.text = field

            DrawableCompat.setTint(
                mFieldInput.background,
                if (field != null)
                    setColor(android.R.color.holo_red_light)
                else
                    primaryColor
            )
            mHint.setTextColor(
                if (field != null)
                    setColor(android.R.color.holo_red_light)
                else
                    primaryColor
            )
        }

    var focus: Boolean = true
        set(value) {
            field = value

            mFieldInput.isFocusable = focus
        }

    var hint: String? = null
        set(value) {
            field = value

            mHint.text = hint
            mFieldInput.hint = hint

            mFieldInput.setOnFocusChangeListener { _, hasFocus ->

                mFieldInput.hint = if (hasFocus) null else hint

                if (text.isNotEmpty())
                    mHint.animate()
                        .alpha(1F)
                        .translationY(10F)
                        .setDuration(300)
                        .start()
                else
                    mHint.animate()
                        .alpha(if (hasFocus) 1F else 0F)
                        .translationY(if (hasFocus) 10F else 30F)
                        .setDuration(300)
                        .start()

                if (errorMessage == null) {
                    mHint.setTextColor(if (hasFocus) primaryColor else setColor(android.R.color.darker_gray))

                    DrawableCompat.setTint(
                        mFieldInput.background,
                        if (hasFocus)
                            primaryColor
                        else
                            setColor(android.R.color.darker_gray)
                    )
                }
            }
        }

    var inputType: Int = 0
        set(value) {
            field = value

            mFieldInput.inputType = field
        }

    @Suppress("DEPRECATION")
    var style: Int = 0
        set(value) {
            field = value

            if (field != 0)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    mFieldInput.setTextAppearance(field)
                else
                    mFieldInput.setTextAppearance(ctx, field)
        }

    var text: String = ""
        get() = mFieldInput.text.toString()
        set(value) {
            field = value

            if (field.isEmpty()) mFieldInput.hint = hint

            mFieldInput.setText(field)
        }

    var textAllCaps: Boolean = false
        set(value) {
            field = value

            mFieldInput.isAllCaps = field
        }

    var textColor: Int = 0
        set(value) {
            field = value

            if (field != 0) mFieldInput.setTextColor(setColor(field))
        }

    var textSize: Float = 0F
        set(value) {
            field = value

            if (field != 0F) mFieldInput.textSize = field / 2F
        }

    init {
        mView = LayoutInflater.from(ctx).inflate(R.layout.field_input, this, true)

        val ta = ctx.obtainStyledAttributes(attrs, R.styleable.FieldInput, defStyle, defStyleRes)

        drawableEnd = ta.getResourceId(R.styleable.FieldInput_drawableEnd, 0)
        focus = ta.getBoolean(R.styleable.FieldInput_focusable, true)
        hint = ta.getString(R.styleable.FieldInput_hint)
        inputType = ta.getInt(R.styleable.FieldInput_inputType, InputType.TYPE_CLASS_TEXT)
        style = ta.getResourceId(R.styleable.FieldInput_style, 0)
        textAllCaps = ta.getBoolean(R.styleable.FieldInput_textAllCaps, false)
        textColor = ta.getResourceId(R.styleable.FieldInput_textColor, 0)
        textSize = ta.getDimension(R.styleable.FieldInput_textSize, 0F)

        ta.recycle()
    }

    /**
     * Do action when user click on drawable
     *
     * @param drawablePosition = set with drawable you want to action click. DrawablePosition(START or END)
     * @param onClickDrawableListener = higher order function lambda, to do something when user clicked the drawable of field.
     */
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

    /**
     * Get the text or do something when user is still typing.
     *
     * @param text = higher order function lambda, to get real time text input
     */
    fun setOnTextChanged(text: (String) -> Unit) {
        mFieldInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                text(s.toString())
            }

        })
    }

    /**
     * Set color for component needed.
     */
    private fun setColor(color: Int) = ContextCompat.getColor(ctx, color)

    /**
     * Destroy component when detached from window
     */
    override fun onDetachedFromWindow() {
        mView = null
        super.onDetachedFromWindow()
    }

}