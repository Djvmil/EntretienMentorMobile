package com.djvmil.data.source.db.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.djvmil.DatabaseSource
import com.djvmil.data.source.db.util.THROW_QUERY_INSERT_COMMENT_EXCEPTION
import com.djvmil.sqldelight.CommentTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class CommentDaoImpl(
    db: DatabaseSource
) : CommentDao {

    val query = db.comment_tableQueries
    override fun getAll(movieId: Int): Flow<List<CommentTable>> =
        query.getAll(movieId.toLong())
            .asFlow()
            .mapToList(Dispatchers.IO)

    override suspend fun insertAll(comments: List<CommentTable>) {
        query.transaction {
            afterRollback { throw Exception(THROW_QUERY_INSERT_COMMENT_EXCEPTION) }

            comments.forEach { comment ->
                query.insert(
                    id = comment.id,
                    title = comment.title,
                    movieId = comment.movieId
                )
            }
        }
    }

    override suspend fun insert(comment: CommentTable) {
        query.insert(
            id = comment.id,
            title = comment.title,
            movieId = comment.movieId
        )
    }

    override suspend fun deleteAll() {
        query.deleteAll()
    }
}