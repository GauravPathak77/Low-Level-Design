public class AdapterPattern {
    public interface IReports {
        String getJsonData(String data);
    }

    public static class XmlDataProvider {
        String xmlData(String data) {
            int sep = data.indexOf(":");
            String name = data.substring(0, sep);
            String id = data.substring(sep + 1);

            return "<user>"
                + "<name>" + name + "</name>"
                + "<id>"   + id   + "</id>"
                + "</user>";
        }
    }

    public static class XmlDataProviderAdapter implements IReports {
        private XmlDataProvider xmlProvider;
        public XmlDataProviderAdapter(XmlDataProvider provider) {
            this.xmlProvider = provider;
        }

        public String getJsonData(String data) {
            String xml = xmlProvider.xmlData(data);

            int startName = xml.indexOf("<name>") + 6;
            int endName   = xml.indexOf("</name>");
            String name   = xml.substring(startName, endName);

            int startId = xml.indexOf("<id>") + 4;
            int endId   = xml.indexOf("</id>");
            String id    = xml.substring(startId, endId);

            // 3. Build and return JSON
            return "{\"name\":\"" + name + "\", \"id\":" + id + "}";
        }
    }

    static class Client {
        public void getReport(IReports report, String rawData) {
            System.out.println("Processed JSON: " + report.getJsonData(rawData));
        }
    }

    public static void main(String[] args) {
        // 1. Create the adaptee
        XmlDataProvider xmlProv = new XmlDataProvider();

        // 2. Make our adapter
        IReports adapter = new XmlDataProviderAdapter(xmlProv);

        // 3. Give it some raw data
        String rawData = "Alice:42";

        // 4. Client prints the JSON
        Client client = new Client();

        client.getReport(adapter, rawData);
    }
}
