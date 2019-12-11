/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeywordDrivenTestFramework.Utilities;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import fr.bmartel.speedtest.model.SpeedTestMode;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

/**
 *
 * @author Ferdinand
 */
public class SpeedTestUtility
{
    // instantiate speed examples
        final SpeedTestSocket speedTestSocket = new SpeedTestSocket();

        private final static int SOCKET_TIMEOUT = 5000;

    /**
     * speed examples server host name.
     */
    private final static String SPEED_TEST_SERVER_HOST = "2.testdebit.info";

    /**
     * spedd examples server uri.
     */
    private final static String SPEED_TEST_SERVER_URI_DL = "/fichiers/1Mo.dat";

    /**,
     * speed examples server port.
     */
    private final static int SPEED_TEST_SERVER_PORT = 80;

    private static Logger LOGGER;

    public SpeedTestUtility() {
        this.LOGGER = LogManager.getLogger(SpeedTestUtility.class.getName());
       
         
    }
    
   
    
     public void runDLTest() 
     {
        //set timeout for download
        speedTestSocket.setSocketTimeout(SOCKET_TIMEOUT);

        // add a listener to wait for speed examples completion and progress
        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {

            @Override
            public void onDownloadFinished(final SpeedTestReport report) {

                LogUtils.logFinishedTask(SpeedTestMode.DOWNLOAD, report.getTotalPacketSize(),
                        report.getTransferRateBit(),
                        report.getTransferRateOctet(), LOGGER);

            }

            @Override
            public void onDownloadError(final SpeedTestError speedTestError, final String errorMessage) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Download error " + speedTestError + " : " + errorMessage);
                }
            }

            @Override
            public void onUploadFinished(final SpeedTestReport report) {

                LogUtils.logFinishedTask(SpeedTestMode.UPLOAD, report.getTotalPacketSize(),
                        report.getTransferRateBit(),
                        report.getTransferRateOctet(), LOGGER);

            }

            @Override
            public void onUploadError(final SpeedTestError speedTestError, final String errorMessage) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Upload error " + speedTestError + " : " + errorMessage);
                }
            }

            @Override
            public void onDownloadProgress(final float percent, final SpeedTestReport downloadReport) {

                LogUtils.logSpeedTestReport(downloadReport, LOGGER);
            }

            @Override
            public void onUploadProgress(final float percent, final SpeedTestReport uploadReport) {

                LogUtils.logSpeedTestReport(uploadReport, LOGGER);
            }

            @Override
            public void onInterruption() {
                //triggered when forceStopTask is called
            }

        });
        
        

        speedTestSocket.startDownload(SPEED_TEST_SERVER_HOST, SPEED_TEST_SERVER_PORT, SPEED_TEST_SERVER_URI_DL);
     }
}