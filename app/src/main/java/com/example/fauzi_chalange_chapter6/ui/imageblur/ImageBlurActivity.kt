package com.example.fauzi_chalange_chapter6.ui.imageblur

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import coil.load
import com.example.fauzi_chalange_chapter6.R
import com.example.fauzi_chalange_chapter6.databinding.ActivityImageBlurBinding
import com.nareshchocha.filepickerlibrary.models.PickMediaConfig
import com.nareshchocha.filepickerlibrary.models.PickMediaType
import com.nareshchocha.filepickerlibrary.ui.FilePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageBlurActivity : AppCompatActivity() {
    companion object {
        const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
    }
    private val viewModel: ImageBlurViewModel by viewModel()
    private lateinit var binding: ActivityImageBlurBinding

    private val filePickerResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::handleFilePickerResult,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBlurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBlur.setOnClickListener { viewModel.applyBlur(blurLevel) }
        binding.btnCancel.setOnClickListener { viewModel.cancelWork() }

        binding.btnSelectImage.setOnClickListener { openFilePicker() }

        viewModel.outputWorkInfos.observe(this, workInfosObserver())
    }
    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->

            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }
            val workInfo = listOfWorkInfo[0]
            if (workInfo.state.isFinished) {
                showWorkFinished()
                val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)

                if (!outputImageUri.isNullOrEmpty()) {
                    viewModel.setOutputUri(outputImageUri)
                    binding.imageSelect.load(outputImageUri)
                }
            } else {
                showWorkInProgress()
            }
        }
    }

    private fun showWorkInProgress() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            btnCancel.visibility = View.VISIBLE
            btnBlur.visibility = View.GONE

        }
    }

    private fun showWorkFinished() {
        with(binding) {
            progressBar.visibility = View.GONE
            btnCancel.visibility = View.GONE
            btnBlur.visibility = View.VISIBLE
        }
    }

    private val blurLevel: Int
        get() =
            when (binding.radioBlurImage.checkedRadioButtonId) {
                R.id.blur_lv1 -> 1
                R.id.blur_lv2 -> 2
                R.id.blur_lv3 -> 3
                R.id.blur_lv3 -> 4
                else -> 1
            }

    private fun openFilePicker() {
        filePickerResult.launch(
            FilePicker.Builder(this)
                .pickMediaBuild(
                    PickMediaConfig(
                        mPickMediaType = PickMediaType.ImageOnly,
                        allowMultiple = false,
                    ),
                ),
        )
    }

    private fun handleFilePickerResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let {
                binding.imageSelect.load(it)
                viewModel.setImageUri(it)
            }
        }
    }
}