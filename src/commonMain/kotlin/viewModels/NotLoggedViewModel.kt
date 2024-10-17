package viewModels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import repository.Token
import repository.TokenFetch

class NotLoggedViewModel(//viewModel: ReceiveViewModel
): ScreenModel {

//    private var _tokensList = MutableStateFlow<List<Token>>(emptyList())
//    var tokensList: StateFlow<List<Token>> = _tokensList
//
//
//
//
//    init {
//        screenModelScope.launch(Dispatchers.IO) {
//            tokenSomething().collect {
//                _tokensList.value = it
//            }
//        }
//    }

}