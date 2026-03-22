package uk.ac.tees.mad.caloriedish.domain.usecase

import uk.ac.tees.mad.caloriedish.data.local.RecentSearchEntity
import uk.ac.tees.mad.caloriedish.domain.repository.DishDbRepository

class InsertRecentUseCase (private val repository: DishDbRepository){
    suspend operator fun invoke(recentSearch: RecentSearchEntity) {
        repository.insertRecentSearch(recentSearch)
    }
}