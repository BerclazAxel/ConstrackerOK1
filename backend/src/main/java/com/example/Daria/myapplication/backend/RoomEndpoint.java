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
        name = "roomApi",
        version = "v1",
        resource = "room",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Daria.example.com",
                ownerName = "backend.myapplication.Daria.example.com",
                packagePath = ""
        )
)
public class RoomEndpoint {

    private static final Logger logger = Logger.getLogger(RoomEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Room.class);
    }

    /**
     * Returns the {@link Room} with the corresponding ID.
     *
     * @param idRoom the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Room} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "room/{idRoom}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Room get(@Named("idRoom") long idRoom) throws NotFoundException {
        logger.info("Getting Room with ID: " + idRoom);
        Room room = ofy().load().type(Room.class).id(idRoom).now();
        if (room == null) {
            throw new NotFoundException("Could not find Room with ID: " + idRoom);
        }
        return room;
    }

    /**
     * Inserts a new {@code Room}.
     */
    @ApiMethod(
            name = "insert",
            path = "room",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Room insert(Room room) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that room.idRoom has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(room).now();
        logger.info("Created Room with ID: " + room.getIdRoom());

        return ofy().load().entity(room).now();
    }

    /**
     * Updates an existing {@code Room}.
     *
     * @param idRoom the ID of the entity to be updated
     * @param room   the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code idRoom} does not correspond to an existing
     *                           {@code Room}
     */
    @ApiMethod(
            name = "update",
            path = "room/{idRoom}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Room update(@Named("idRoom") long idRoom, Room room) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(idRoom);
        ofy().save().entity(room).now();
        logger.info("Updated Room: " + room);
        return ofy().load().entity(room).now();
    }

    /**
     * Deletes the specified {@code Room}.
     *
     * @param idRoom the ID of the entity to delete
     * @throws NotFoundException if the {@code idRoom} does not correspond to an existing
     *                           {@code Room}
     */
    @ApiMethod(
            name = "remove",
            path = "room/{idRoom}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("idRoom") long idRoom) throws NotFoundException {
        checkExists(idRoom);
        ofy().delete().type(Room.class).id(idRoom).now();
        logger.info("Deleted Room with ID: " + idRoom);
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
            path = "room",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Room> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Room> query = ofy().load().type(Room.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Room> queryIterator = query.iterator();
        List<Room> roomList = new ArrayList<Room>(limit);
        while (queryIterator.hasNext()) {
            roomList.add(queryIterator.next());
        }
        return CollectionResponse.<Room>builder().setItems(roomList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(long idRoom) throws NotFoundException {
        try {
            ofy().load().type(Room.class).id(idRoom).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Room with ID: " + idRoom);
        }
    }
}