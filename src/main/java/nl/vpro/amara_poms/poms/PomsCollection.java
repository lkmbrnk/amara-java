package nl.vpro.amara_poms.poms;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.vpro.domain.media.update.GroupUpdate;
import nl.vpro.domain.media.update.MemberUpdate;
import nl.vpro.rs.media.MediaRestClient;


/**
 * @author joost
 */
public class PomsCollection {

    final Logger logger = LoggerFactory.getLogger(PomsCollection.class);
    static final int ERROR_COLLECTION_NOT_FOUND = 1;
    static final int ERROR_BROADCAST_NOT_FOUND = 3;

    String collectionName;

    GroupUpdate group;

    Iterable<MemberUpdate> memberUpdateArrayList;

    public Iterator<MemberUpdate> getBroadcastsIterator() {
        return memberUpdateArrayList.iterator();
    }

    public GroupUpdate getGroup() {
        return group;
    }

    public PomsCollection(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * Get collection from POMS
     *
     * @return 0 if found, error code != 0  if not found
     */
    public int getBroadcastsFromPOMS() {
        int returnValue = 0;
        MediaRestClient client = Utils.getClient();

        group = client.getGroup(collectionName); // get meta info for collection

        if (group == null) {
            returnValue = ERROR_COLLECTION_NOT_FOUND;
        }
        else
        {
            memberUpdateArrayList = client.getGroupMembers(collectionName); // get group numbers
        }

        return returnValue;
    }

}
