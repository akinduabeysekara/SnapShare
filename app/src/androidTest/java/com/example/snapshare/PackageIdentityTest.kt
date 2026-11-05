package com.example.snapshare

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PackageIdentityTest {
    @Test
    fun appRunsUnderExpectedPackage() {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.snapshare", targetContext.packageName)
    }
}
