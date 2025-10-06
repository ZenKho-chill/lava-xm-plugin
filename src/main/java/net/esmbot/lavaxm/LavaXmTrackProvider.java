package net.esmbot.lavaxm;

import com.sedmelluq.discord.lavaplayer.filter.AudioPipeline;
import com.sedmelluq.discord.lavaplayer.filter.AudioPipelineFactory;
import com.sedmelluq.discord.lavaplayer.filter.PcmFormat;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioProcessingContext;
import org.helllabs.libxmp.FrameInfo;
import org.helllabs.libxmp.Player;
import org.helllabs.libxmp.Xmp;

public class LavaXmTrackProvider {
    private final Player player;
    private final AudioPipeline downstream;
    private final LavaXmConfig config;
    private final FrameInfo info = new FrameInfo();

    public LavaXmTrackProvider(AudioProcessingContext context, Player player, LavaXmConfig config) {
        this.player = player;
        this.config = config;
        this.downstream = AudioPipelineFactory.create(context, new PcmFormat(2, player.getSamplingRate()));
    }

    public void provideFrames() {
        this.player.start();
        this.player.setAmplificationFactor(this.config.getAmpFactor());
        this.player.setInterpolation(this.config.getInterpolation());
        int playerFlags = (this.config.getVblank() ? Xmp.FLAGS_VBLANK : 0)
                            | (this.config.getFx9Bug() ? Xmp.FALGS_FX9BUG : 0)
                            | (this.config.getFixSampleLoop() ? Xmp.FLAGS_FIXLOOp : 0)
                            | (this.config.getAmigaMixer() ? Xmp.FLAGS_A500 : 0);
        this.player.setFlags(playerFlags);

        while (this.player.playerFrame(this.info)) {
            if (this.info.getLoopCount() > 0) {
                break;
            }

            int bufSize = this.info.getBufferSize();
            short[] shortBuffer = new short[bufSize / 2];
            byte[] buffer = this.info.getBufferArray();
            for (int i = 0; i < bufSizel i+= 2) {
                shortBuffer[i / 2] = (short)(((buffer[i + 1] & 0xFF) << 8) | (buffer[i] & 0xFF));
            }

            try {
                downstream.process(shortBuffer, 0, bufSize / 2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.player.end();
    }
    public void close() {
        this.player.end();
        this.downstream.close();
    }
}