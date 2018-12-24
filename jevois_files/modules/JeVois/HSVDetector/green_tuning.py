#hsv pipeline for green 

import libjevois as jevois
import cv2
import numpy as np
import time

class Green_HSVDetector:
    # ###################################################################################################
    ## Constructor
    def __init__(self):
        # Instantiate a JeVois S to measure our processing framerate:
        # self.timer = jevois.Timer("sandbox", 100, jevois.LOG_INFO)

        self.outimg = None

        # SPECIAL REPLACED BLUR CONSTANT
        self.__blur_type = 0

    # ###################################################################################################
        # ALL CONSTANTS GO UNDER HERE (make sure to remove the self.__blur_type line)

        self.__blur_type = BlurType.Median_Filter
        self.__blur_radius = 9.178990311065784

        self.blur_output = None

        self.__hsv_threshold_input = self.blur_output
        self.__hsv_threshold_hue = [29.746372393610535, 60.105980721094966]
        self.__hsv_threshold_saturation = [94.01978417266191, 255.0]
        self.__hsv_threshold_value = [0.0, 255.0]

        self.hsv_threshold_output = None

        self.__cv_erode_src = self.hsv_threshold_output
        self.__cv_erode_kernel = None
        self.__cv_erode_anchor = (-1, -1)
        self.__cv_erode_iterations = 6.0
        self.__cv_erode_bordertype = cv2.BORDER_CONSTANT
        self.__cv_erode_bordervalue = (-1)

        self.cv_erode_output = None

        self.__cv_dilate_src = self.cv_erode_output
        self.__cv_dilate_kernel = None
        self.__cv_dilate_anchor = (-1, -1)
        self.__cv_dilate_iterations = 6.0
        self.__cv_dilate_bordertype = cv2.BORDER_CONSTANT
        self.__cv_dilate_bordervalue = (-1)

        self.cv_dilate_output = None

        self.__find_contours_input = self.cv_dilate_output
        self.__find_contours_external_only = True

        self.find_contours_output = None

        self.__filter_contours_contours = self.find_contours_output
        self.__filter_contours_min_area = 800.0
        self.__filter_contours_min_perimeter = 0.0
        self.__filter_contours_min_width = 0.0
        self.__filter_contours_max_width = 100000.0
        self.__filter_contours_min_height = 0.0
        self.__filter_contours_max_height = 100000.0
        self.__filter_contours_solidity = [0.0, 100.0]
        self.__filter_contours_max_vertices = 10000.0
        self.__filter_contours_min_vertices = 0.0
        self.__filter_contours_min_ratio = 0.0
        self.__filter_contours_max_ratio = 100.0

        self.filter_contours_output = None

        # self.start_time = 0
        # self.end_time = 0

        # END CONSTANTS
    # ###################################################################################################

    ## Process function with USB output

    def processNoUSB(self, inframe):
        source0 = inimg = inframe.getCvBGR()
        self.outimg = inimg = inframe.getCvBGR()

        # Start measuring image processing time (NOTE: does not account for input conversion time):
        # self.timer.start()
        # self.start_time = time.time()

#################################################################################################

        # BEGIN GRIP CODE

#################################################################################################
        """
        Runs the pipeline and sets all outputs to new values.
        """
      
        # Step Blur0:
        self.__blur_input = source0
        (self.blur_output) = self.__blur(self.__blur_input, self.__blur_type, self.__blur_radius)

        # Step HSV_Threshold0:
        self.__hsv_threshold_input = self.blur_output
        (self.hsv_threshold_output) = self.__hsv_threshold(self.__hsv_threshold_input, self.__hsv_threshold_hue, self.__hsv_threshold_saturation, self.__hsv_threshold_value)

        # Step CV_erode0:
        self.__cv_erode_src = self.hsv_threshold_output
        (self.cv_erode_output) = self.__cv_erode(self.__cv_erode_src, self.__cv_erode_kernel, self.__cv_erode_anchor, self.__cv_erode_iterations, self.__cv_erode_bordertype, self.__cv_erode_bordervalue)

        # Step CV_dilate0:
        self.__cv_dilate_src = self.cv_erode_output
        (self.cv_dilate_output) = self.__cv_dilate(self.__cv_dilate_src, self.__cv_dilate_kernel, self.__cv_dilate_anchor, self.__cv_dilate_iterations, self.__cv_dilate_bordertype, self.__cv_dilate_bordervalue)

        # Step Find_Contours0:
        self.__find_contours_input = self.cv_dilate_output
        (self.find_contours_output) = self.__find_contours(self.__find_contours_input, self.__find_contours_external_only)

        # Step Filter_Contours0:
        self.__filter_contours_contours = self.find_contours_output
        (self.filter_contours_output) = self.__filter_contours(self.__filter_contours_contours, self.__filter_contours_min_area, self.__filter_contours_min_perimeter, self.__filter_contours_min_width, self.__filter_contours_max_width, self.__filter_contours_min_height, self.__filter_contours_max_height, self.__filter_contours_solidity, self.__filter_contours_max_vertices, self.__filter_contours_min_vertices, self.__filter_contours_min_ratio, self.__filter_contours_max_ratio)



#################################################################################################

        # END GRIP CODE

##################################################################################################

        # DEFAULT CUSTOM CODE

        def getArea(con): # Gets the area of the contour
            return cv2.contourArea(con)

        def getYcoord(con): # Gets the Y coordinate of the contour center
            M = cv2.moments(con)
            cy = int(M['m01']/M['m00'])
            return cy

        def getXcoord(con): # Gets the X coordinate of the contour center
            M = cv2.moments(con)
            cy = int(M['m10']/M['m00'])
            return cy

        def sortByArea(conts) : # Returns an array sorted by area from smallest to largest
            contourNum = len(conts) # Gets number of contours
            sortedBy = sorted(conts, key=getArea) # sortedBy now has all the contours sorted by area
            return sortedBy

##################################################################################################

        # PUT YOUR CUSTOM CODE HERE

##################################################################################################

        # Draws all contours on original image in red
        # cv2.drawContours(self.outimg, self.filter_contours_output, -1, (0, 0, 255), 1)

        # Gets number of contours
        contourNum = len(self.filter_contours_output)

        # Sorts contours by the smallest area first
        newContours = sortByArea(self.filter_contours_output)

        # Send the contour data over Serial
        for i in range (contourNum):
            cnt = newContours[i]
            x,y,w,h = cv2.boundingRect(cnt) # Get the stats of the contour including width and height

            # which contour, 0 is first
            toSend = ("/" + str(i) +
                     "/" + str(getArea(cnt)) +  # Area of contour
                     "/" + str(round(getXcoord(cnt)-160, 2)) +  # x-coordinate of centroid of contour, -160 to 160 rounded to 2 decimal
                     "/" + str(round(120-getYcoord(cnt), 2)) +  # y-coordinate of contour, -120 to 120 rounded to 2 decimal
                     "/" + str(round(h, 2)) +  # Height of contour, 0-320 rounded to 2 decimal
                     "/" + str(round(w, 2))) # Width of contour, 0-240 rounded to 2 decimal
            jevois.sendSerial(toSend)

    def process(self, inframe, outframe):
        self.processNoUSB(inframe)



##################################################################################################

        # END CUSTOM CODE

###################################################################################################

    # FUNCTIONS GO HERE (Anything that starts with "@staticmethod")

    @staticmethod
    def __blur(src, type, radius):
        """Softens an image using one of several filters.
        Args:
            src: The source mat (numpy.ndarray).
            type: The blurType to perform represented as an int.
            radius: The radius for the blur as a float.
        Returns:
            A numpy.ndarray that has been blurred.
        """
        if(type is BlurType.Box_Blur):
            ksize = int(2 * round(radius) + 1)
            return cv2.blur(src, (ksize, ksize))
        elif(type is BlurType.Gaussian_Blur):
            ksize = int(6 * round(radius) + 1)
            return cv2.GaussianBlur(src, (ksize, ksize), round(radius))
        elif(type is BlurType.Median_Filter):
            ksize = int(2 * round(radius) + 1)
            return cv2.medianBlur(src, ksize)
        else:
            return cv2.bilateralFilter(src, -1, round(radius), round(radius))

    @staticmethod
    def __hsv_threshold(input, hue, sat, val):
        """Segment an image based on hue, saturation, and value ranges.
        Args:
            input: A BGR numpy.ndarray.
            hue: A list of two numbers the are the min and max hue.
            sat: A list of two numbers the are the min and max saturation.
            lum: A list of two numbers the are the min and max value.
        Returns:
            A black and white numpy.ndarray.
        """
        out = cv2.cvtColor(input, cv2.COLOR_BGR2HSV)
        return cv2.inRange(out, (hue[0], sat[0], val[0]),  (hue[1], sat[1], val[1]))

    @staticmethod
    def __cv_erode(src, kernel, anchor, iterations, border_type, border_value):
        """Expands area of lower value in an image.
        Args:
           src: A numpy.ndarray.
           kernel: The kernel for erosion. A numpy.ndarray.
           iterations: the number of times to erode.
           border_type: Opencv enum that represents a border type.
           border_value: value to be used for a constant border.
        Returns:
            A numpy.ndarray after erosion.
        """
        return cv2.erode(src, kernel, anchor, iterations = (int) (iterations +0.5),
                            borderType = border_type, borderValue = border_value)

    @staticmethod
    def __cv_dilate(src, kernel, anchor, iterations, border_type, border_value):
        """Expands area of higher value in an image.
        Args:
           src: A numpy.ndarray.
           kernel: The kernel for dilation. A numpy.ndarray.
           iterations: the number of times to dilate.
           border_type: Opencv enum that represents a border type.
           border_value: value to be used for a constant border.
        Returns:
            A numpy.ndarray after dilation.
        """
        return cv2.dilate(src, kernel, anchor, iterations = (int) (iterations +0.5),
                            borderType = border_type, borderValue = border_value)

    @staticmethod
    def __find_contours(input, external_only):
        """Sets the values of pixels in a binary image to their distance to the nearest black pixel.
        Args:
            input: A numpy.ndarray.
            external_only: A boolean. If true only external contours are found.
        Return:
            A list of numpy.ndarray where each one represents a contour.
        """
        if(external_only):
            mode = cv2.RETR_EXTERNAL
        else:
            mode = cv2.RETR_LIST
        method = cv2.CHAIN_APPROX_SIMPLE
        im2, contours, hierarchy =cv2.findContours(input, mode=mode, method=method)
        return contours

    @staticmethod
    def __filter_contours(input_contours, min_area, min_perimeter, min_width, max_width,
                        min_height, max_height, solidity, max_vertex_count, min_vertex_count,
                        min_ratio, max_ratio):
        """Filters out contours that do not meet certain criteria.
        Args:
            input_contours: Contours as a list of numpy.ndarray.
            min_area: The minimum area of a contour that will be kept.
            min_perimeter: The minimum perimeter of a contour that will be kept.
            min_width: Minimum width of a contour.
            max_width: MaxWidth maximum width.
            min_height: Minimum height.
            max_height: Maximimum height.
            solidity: The minimum and maximum solidity of a contour.
            min_vertex_count: Minimum vertex Count of the contours.
            max_vertex_count: Maximum vertex Count.
            min_ratio: Minimum ratio of width to height.
            max_ratio: Maximum ratio of width to height.
        Returns:
            Contours as a list of numpy.ndarray.
        """
        output = []
        for contour in input_contours:
            x,y,w,h = cv2.boundingRect(contour)
            if (w < min_width or w > max_width):
                continue	
            if (h < min_height or h > max_height):
                continue
            area = cv2.contourArea(contour)
            if (area < min_area):
                continue
            if (cv2.arcLength(contour, True) < min_perimeter):
                continue
            hull = cv2.convexHull(contour)
            solid = 100 * area / cv2.contourArea(hull)
            if (solid < solidity[0] or solid > solidity[1]):
                continue
            if (len(contour) < min_vertex_count or len(contour) > max_vertex_count):
                continue
            ratio = (float)(w) / h
            if (ratio < min_ratio or ratio > max_ratio):
                continue
            output.append(contour)
        return output


#blur makes things weird when we test so commented out for now 
#BlurType = Enum('BlurType', 'Box_Blur Gaussian_Blur Median_Filter Bilateral_Filter')