package com.example.Daria.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "topicApi",
        version = "v1",
        resource = "topic",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Daria.example.com",
                ownerName = "backend.myapplication.Daria.example.com",
                packagePath = ""
        )
)
public class TopicEndpoint {

    private static final Logger logger = Logger.getLogger(TopicEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Topic.class);
    }

    /**
     * Returns the {@link Topic} with the corresponding ID.
     *
     * @param idTopic the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Topic} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "topic/{idTopic}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Topic get(@Named("idTopic") int idTopic) throws NotFoundException {
        logger.info("Getting Topic with ID: " + idTopic);
        Topic topic = ofy().load().type(Topic.class).id(idTopic).now();
        if (topic == null) {
            throw new NotFoundException("Could not find Topic with ID: " + idTopic);
        }
        return topic;
    }

    /**
     * Inserts a new {@code Topic}.
     */
    @ApiMethod(
            name = "insert",
            path = "topic",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Topic insert(Topic topic) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that topic.idTopic has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(topic).now();
        logger.info("Created Topic with ID: " + topic.getIdTopic());

        return ofy().load().entity(topic).now();
    }

    /**
     * Updates an existing {@code Topic}.
     *
     * @param idTopic the ID of the entity to be updated
     * @param topic   the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code idTopic} does not correspond to an existing
     *                           {@code Topic}
     */
    @ApiMethod(
            name = "update",
            path = "topic/{idTopic}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Topic update(@Named("idTopic") int idTopic, Topic topic) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(idTopic);
        ofy().save().entity(topic).now();
        logger.info("Updated Topic: " + topic);
        return ofy().load().entity(topic).now();
    }

    /**
     * Deletes the specified {@code Topic}.
     *
     * @param idTopic the ID of the entity to delete
     * @throws NotFoundException if the {@code idTopic} does not correspond to an existing
     *                           {@code Topic}
     */
    @ApiMethod(
            name = "remove",
            path = "topic/{idTopic}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("idTopic") int idTopic) throws NotFoundException {
        checkExists(idTopic);
        ofy().delete().type(Topic.class).id(idTopic).now();
        logger.info("Deleted Topic with ID: " + idTopic);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "topic",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Topic> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Topic> query = ofy().load().type(Topic.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Topic> queryIterator = query.iterator();
        List<Topic> topicList = new ArrayList<Topic>(limit);
        while (queryIterator.hasNext()) {
            topicList.add(queryIterator.next());
        }
        return CollectionResponse.<Topic>builder().setItems(topicList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(int idTopic) throws NotFoundException {
        try {
            ofy().load().type(Topic.class).id(idTopic).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Topic with ID: " + idTopic);
        }
    }
}