var imageUpload = document.getElementById('imageUpload')
var img1=document.getElementById('img1')
Promise.all([
    faceapi.nets.faceRecognitionNet.loadFromUri('/models'),
    faceapi.nets.faceLandmark68Net.loadFromUri('/models'),
    faceapi.nets.ssdMobilenetv1.loadFromUri('/models')
]).then(start)

async function start() {
    var container = document.createElement('div')
    container.style.position = 'relative'
    document.body.append(container)
    const labeledFaceDescriptors = await loadLabeledImages()
    var faceMatcher = new faceapi.FaceMatcher(labeledFaceDescriptors)
    let image
    let canvas
    document.body.append('Loaded')
    imageUpload.addEventListener('click', async () => {
        if (image) image.remove()
        if (canvas) canvas.remove()
        //image = await faceapi.bufferToImage(file1)
        container.append(img1)
        canvas = faceapi.createCanvasFromMedia(img1)
        container.append(canvas)
        const displaySize = { width: img1.width, height: img1.height }
        faceapi.matchDimensions(canvas, displaySize)
        const detections = await faceapi.detectAllFaces(img1).withFaceLandmarks().withFaceDescriptors()
        const resizedDetections = faceapi.resizeResults(detections, displaySize)
        const results = resizedDetections.map(d => faceMatcher.findBestMatch(d.descriptor))
    results.forEach((result, i) => {

        const box = resizedDetections[i].detection.box
        const drawBox = new faceapi.draw.DrawBox(box, { label: result.toString() })
        var result1 = JSON.stringify(result.toString())
        var nstr = result1.replace(/unknown/g,"")
        var result2=nstr.replace(/\([^\)]*\)/g,"")
        var result3=result2.replace(/\s+/g,"")
        var result4=result3.replace(/\"/g, "")
         alert(result4)
        var result5="&name="+result4
    $.ajax({
        url: "/getId",
        type: "POST",
        data: result5,
         dataType:"json",
    });

        drawBox.draw(canvas)
    })//获得名字
})
}

function loadLabeledImages() {
    // alert(name+"2222");
    // alert(name[1]);
    // alert(name);
    const labels = arr
    return Promise.all(
        labels.map(async label => {
        const descriptions = []
            const img = await faceapi.fetchImage(`/labeled_images/${label}/1.png`)
            const detections = await faceapi.detectSingleFace(img).withFaceLandmarks().withFaceDescriptor()
            descriptions.push(detections.descriptor)


        return new faceapi.LabeledFaceDescriptors(label, descriptions)
    })
)
}
