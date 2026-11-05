package com.example.snapshare.photo

import androidx.exifinterface.media.ExifInterface
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ExifRotationTest {
    @Test
    fun rotatedOrientationsMapToTheirDegrees() {
        assertEquals(90f, rotationForExifOrientation(ExifInterface.ORIENTATION_ROTATE_90))
        assertEquals(180f, rotationForExifOrientation(ExifInterface.ORIENTATION_ROTATE_180))
        assertEquals(270f, rotationForExifOrientation(ExifInterface.ORIENTATION_ROTATE_270))
    }

    @Test
    fun otherOrientationsNeedNoRotation() {
        assertNull(rotationForExifOrientation(ExifInterface.ORIENTATION_NORMAL))
        assertNull(rotationForExifOrientation(ExifInterface.ORIENTATION_UNDEFINED))
    }
}
