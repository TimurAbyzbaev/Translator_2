package com.example.repository

import com.example.model.data.DataModel
import com.example.model.data.Meaning
import com.example.model.data.TranslatedMeaning
import com.example.model.dto.SearchResultDto
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertSame
import org.junit.Test

class SearchResultUnitTest {
    @Test
    fun convertMeaningsToSingleStringSimpleTest(){
        val translatedMeaningOne = TranslatedMeaning("translatedMeaningOne")
        val translatedMeaningTwo = TranslatedMeaning("translatedMeaningTwo")
        val imageUrlOne = "imageUrlOne"
        val imageUrlTwo = "imageUrlTwo"
        val meaningsList: List<Meaning> = listOf(
            Meaning(translatedMeaningOne,imageUrlOne),
            Meaning(translatedMeaningTwo, imageUrlTwo)
        )
        assertEquals(convertMeaningsToSingleString(meaningsList),
            "translatedMeaningOne, translatedMeaningTwo")
    }
    @Test
    fun convertDataModelToHistoryEntityTest(){
        val dataModel = DataModel("", listOf(Meaning()))
        assertSame(SearchResultDto::class.java, convertDataModelToHistoryEntity(dataModel)::class.java)
    }
}