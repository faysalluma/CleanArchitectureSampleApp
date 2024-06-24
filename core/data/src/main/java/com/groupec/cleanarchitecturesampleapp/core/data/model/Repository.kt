package com.groupec.cleanarchitecturesampleapp.core.data.model


import com.google.samples.apps.nowinandroid.core.database.model.RepositoryEntity
import com.google.samples.apps.nowinandroid.core.model.data.Repository
import com.google.samples.apps.nowinandroid.core.network.model.NetworkRepository

fun NetworkRepository.toRepository() = Repository(
    id = id,
    full_name = full_name,
    description = description,
    languages_url = languages_url,
    stargazers_url = languages_url,
    branches_url = branches_url,
    contributors_url = contributors_url
)

fun RepositoryEntity.toRepository() = Repository(
    id = id,
    full_name = full_name,
    description = description,
    languages_url = languages_url,
    stargazers_url = languages_url,
    branches_url = branches_url,
    contributors_url = contributors_url
)
