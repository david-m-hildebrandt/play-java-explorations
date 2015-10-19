package data;

import com.datastax.driver.core.*;

/**
 * Created by David on 2015-10-18.
 */
public class CassandraClient {

    private Cluster cluster;
    private Session session;

    public void connect(String node) {
        cluster = Cluster.builder()
                .addContactPoint(node)
                .build();
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n",
                metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }
        session = cluster.connect();
    }

    public void close() {
        session.close();
        cluster.close();
    }

    public static void main(String[] args) {
        CassandraClient client = new CassandraClient();
        client.connect("127.0.0.1");
        client.close();
    }

    public static CassandraClient getClient() {
        CassandraClient client = new CassandraClient();
        client.connect("127.0.0.1");
        return client;
    }

    public ResultSet execute(String cqlStatement) {
        return session.execute(cqlStatement);
    }


}
