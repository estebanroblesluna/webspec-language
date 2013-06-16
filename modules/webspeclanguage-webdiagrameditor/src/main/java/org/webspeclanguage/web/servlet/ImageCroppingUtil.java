/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.webspeclanguage.web.servlet;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.webspeclanguage.userstories.cropping.CroppingInfo;

/**
 * Utility class that crops an image based on specific parameters.
 * 
 * @author cristian.cianfagna
 */
public class ImageCroppingUtil {

  private static Log LOGGER = LogFactory.getLog(ProjectRestService.class);

  static Rectangle clip;

  public static ByteArrayOutputStream cropImage(File mainImageFile, CroppingInfo croppingInfo) throws Exception {
    LOGGER.info("........Starting cropping.........");

    BufferedImage originalImage = readImage(mainImageFile);

    BufferedImage processedImage = cropMyImage(originalImage, croppingInfo.getWidth(), croppingInfo.getHeight(), croppingInfo.getX(), croppingInfo.getY());

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    writeImage(processedImage, byteArrayOutputStream, "png");

    LOGGER.info(".......Done Cropping.........");
    return byteArrayOutputStream;
  }

  private static BufferedImage cropMyImage(BufferedImage img, int cropWidth, int cropHeight, int cropStartX, int cropStartY) throws Exception {
    BufferedImage clipped = null;
    Dimension size = new Dimension(cropWidth, cropHeight);

    createClip(img, size, cropStartX, cropStartY);

    try {
      int w = clip.width;
      int h = clip.height;

      LOGGER.info("Crop Width " + w);
      LOGGER.info("Crop Height " + h);
      LOGGER.info("Crop Location " + "(" + clip.x + "," + clip.y + ")");

      clipped = img.getSubimage(clip.x, clip.y, w, h);

      LOGGER.info("Image Cropped. New Image Dimension: " + clipped.getWidth() + "w X " + clipped.getHeight() + "h");
    } catch (RasterFormatException rfe) {
      LOGGER.error("Raster format error: " + rfe.getMessage());
      return null;
    }
    return clipped;
  }

  /**
   * This method crops an original image to the crop parameters provided.
   * 
   * If the crop rectangle lies outside the rectangle (even if partially),
   * adjusts the rectangle to be included within the image area.
   * 
   * @param img
   *          = Original Image To Be Cropped
   * @param size
   *          = Crop area rectangle
   * @param clipX
   *          = Starting X-position of crop area rectangle
   * @param clipY
   *          = Strating Y-position of crop area rectangle
   * @throws Exception
   */
  private static void createClip(BufferedImage img, Dimension size, int clipX, int clipY) throws Exception {
    /**
     * Some times clip area might lie outside the original image, fully or
     * partially. In such cases, this program will adjust the crop area to fit
     * within the original image.
     * 
     * isClipAreaAdjusted flas is usded to denote if there was any adjustment
     * made.
     */
    boolean isClipAreaAdjusted = false;

    /** Checking for negative X Co-ordinate **/
    if (clipX < 0) {
      clipX = 0;
      isClipAreaAdjusted = true;
    }
    /** Checking for negative Y Co-ordinate **/
    if (clipY < 0) {
      clipY = 0;
      isClipAreaAdjusted = true;
    }

    /** Checking if the clip area lies outside the rectangle **/
    if ((size.width + clipX) <= img.getWidth() && (size.height + clipY) <= img.getHeight()) {

      /**
       * Setting up a clip rectangle when clip area lies within the image.
       */

      clip = new Rectangle(size);
      clip.x = clipX;
      clip.y = clipY;
    } else {

      /**
       * Checking if the width of the clip area lies outside the image. If so,
       * making the image width boundary as the clip width.
       */
      if ((size.width + clipX) > img.getWidth())
        size.width = img.getWidth() - clipX;

      /**
       * Checking if the height of the clip area lies outside the image. If so,
       * making the image height boundary as the clip height.
       */
      if ((size.height + clipY) > img.getHeight())
        size.height = img.getHeight() - clipY;

      /**
       * Setting up the clip are based on our clip area size adjustment
       **/
      clip = new Rectangle(size);
      clip.x = clipX;
      clip.y = clipY;

      isClipAreaAdjusted = true;

    }
    if (isClipAreaAdjusted)
      LOGGER.info("Crop Area Lied Outside The Image." + " Adjusted The Clip Rectangle\n");
  }

  /**
   * This method reads an image from the inputStream
   * 
   * @param inputStream
   *          of the original image
   * @return BufferedImage of the file read
   */
  public static BufferedImage readImage(File inputStream) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(inputStream);
      LOGGER.info("Image Read. Image Dimension: " + img.getWidth() + "w X " + img.getHeight() + "h");
    } catch (IOException e) {
      LOGGER.error(e);
    }
    return img;
  }

  /**
   * This method writes a buffered image to a outputStream
   * 
   * @param img
   *          -- > BufferedImage
   * @param outputStream
   *          stream to write the new image
   * @param extension
   *          --> e.g. "jpg","gif","png"
   * @throws IOException
   */
  public static void writeImage(BufferedImage img, OutputStream outputStream, String extension) throws IOException {
    BufferedImage bi = img;
    ImageIO.write(bi, extension, outputStream);
  }

}
