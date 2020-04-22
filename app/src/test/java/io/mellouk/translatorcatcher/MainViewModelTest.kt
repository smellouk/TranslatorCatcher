package io.mellouk.translatorcatcher

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mellouk.translatorcatcher.domain.model.Round
import io.mellouk.translatorcatcher.domain.model.Word
import io.mellouk.translatorcatcher.domain.usecase.getwords.GetWordsDataState
import io.mellouk.translatorcatcher.domain.usecase.getwords.GetWordsUseCase
import io.mellouk.translatorcatcher.domain.usecase.matchwords.MatchWordsDataState
import io.mellouk.translatorcatcher.domain.usecase.matchwords.MatchWordsParams
import io.mellouk.translatorcatcher.domain.usecase.matchwords.MatchWordsUseCase
import io.mellouk.translatorcatcher.domain.usecase.nextround.PrepareDataState
import io.mellouk.translatorcatcher.domain.usecase.nextround.PrepareNextRoundUseCase
import io.mellouk.translatorcatcher.ui.Command
import io.mellouk.translatorcatcher.ui.MainViewModel
import io.mellouk.translatorcatcher.ui.ViewState
import io.mellouk.translatorcatcher.ui.ViewStateMapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class MainViewModelTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var getWordsUseCase: GetWordsUseCase

    @RelaxedMockK
    lateinit var viewStateMapper: ViewStateMapper

    @RelaxedMockK
    lateinit var prepareNextRoundUseCase: PrepareNextRoundUseCase

    @RelaxedMockK
    lateinit var matchWordsUseCase: MatchWordsUseCase

    @InjectMockKs
    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getInitialState_ShouldBeInitial() {
        assertEquals(viewModel.getInitialState(), ViewState.Initial)
    }

    @Test
    fun onGetWordsCommand_ShouldGetWordsSuccessfully() {
        givenGetWordsUseCase()
        givenPrepareNextRoundUseCase()

        viewModel.onCommand(givenGetWordsCommand)

        verify(exactly = once) {
            getWordsUseCase.buildObservable()
            prepareNextRoundUseCase.buildObservable()
            viewStateMapper.map(givenPrepareDataState)
        }
    }

    @Test
    fun onNextRound_ShouldPrepareNextRound() {
        givenPrepareNextRoundUseCase()

        viewModel.onCommand(givenNextRoundCommand)

        verify(exactly = once) {
            prepareNextRoundUseCase.buildObservable()
            viewStateMapper.map(givenPrepareDataState)
        }
    }

    @Test
    fun onMatchWords_ShouldMatchWordsWhenWordsAreSameAndUseSelectedCorrectAnswer() {
        val matchWordsParams = MatchWordsParams(givenStaticId, givenStaticId, true)
        val givenMatchWordsCommand = Command.MatchWords(matchWordsParams)
        givenMatchWordsUseCase(matchWordsParams, MatchWordsDataState.Successful)

        viewModel.onCommand(givenMatchWordsCommand)

        verify(exactly = once) {
            matchWordsUseCase.buildObservable(matchWordsParams)
            viewStateMapper.map(MatchWordsDataState.Successful)
        }
    }

    private fun givenGetWordsUseCase() {
        every {
            getWordsUseCase.buildObservable()
        } returns Observable.just(GetWordsDataState.Successful)
    }

    private fun givenPrepareNextRoundUseCase() {
        every {
            prepareNextRoundUseCase.buildObservable()
        } returns Observable.just(givenPrepareDataState)
    }

    private fun givenMatchWordsUseCase(params: MatchWordsParams, state: MatchWordsDataState) {
        every {
            matchWordsUseCase.buildObservable(params)
        } returns Observable.just(state)
    }
}

private const val once = 1
private val givenStaticId = UUID.randomUUID().toString()
private val givenFallingId = UUID.randomUUID().toString()
private const val englishWord = "english"
private const val spanishWord = "spanish"
private const val givenScore = 0

private val givenGetWordsCommand = Command.GetWords
private val givenNextRoundCommand = Command.NextRound

private val givenStaticWord = Word(givenStaticId, englishWord, spanishWord)
private val givenFallingWord = Word(givenFallingId, englishWord, spanishWord)
private val givenRound = Round(givenStaticWord, givenFallingWord, givenScore)
private val givenPrepareDataState = PrepareDataState.Successful(givenRound)