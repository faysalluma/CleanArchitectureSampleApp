package com.groupec.cleanarchitecturesampleapp.core.data.model

import com.google.samples.apps.nowinandroid.core.network.model.NetworkContributor


fun NetworkContributor.toContributor() = Contributor(
    id = id,
    full_name = full_name,
    description = description,
    languages_url = languages_url,
    stargazers_url = languages_url,
    branches_url = branches_url,
    contributors_url = contributors_url
)

fun ContributoEntity.toContributor() = Contributor(
    id = id,
    full_name = full_name,
    description = description,
    languages_url = languages_url,
    stargazers_url = languages_url,
    branches_url = branches_url,
    contributors_url = contributors_url
)
