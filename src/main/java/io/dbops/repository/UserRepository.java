package io.dbops.repository;

import io.dbops.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends Neo4jRepository<User,Long> {

    User findByUserId(String userId);

    User findByUsername(String username);

    @Query("MATCH (u:User {userId: $userId })<-[:FOLLOWS]-(followers) RETURN followers ORDER BY toLower(followers.username)  LIMIT 25")
    Set<User> followers(@Param("userId") String userId);

    @Query("MATCH (u:User {userId: $userId })-[:FOLLOWS]->(following) RETURN following ORDER BY toLower(following.username) LIMIT 25")
    Set<User> following(@Param("userId") String userId);


    @Query("MATCH  (u:User {userId: $userId }) " +
            "WITH u " +
            "MATCH (f:User {userId: $fid }) " +
            "WITH u, f " +
            "RETURN EXISTS( (u)-[:FOLLOWS]->(f) ) ")
    Boolean follows(@Param("userId") String userId, @Param("fid") String fid);

    @Query("MATCH (u:User {userId: $userId }) " +
            "WITH u " +
            "MATCH (pm:User) WHERE NOT (u)-[:FOLLOWS]->(pm) AND pm.username IS NOT NULL " +
            "RETURN distinct pm  LIMIT 100 ")
    Set<User> potentialMatches(@Param("userId") String userId);






}
